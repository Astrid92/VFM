package mx.ipn.tworisteando;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import mx.ipn.tworisteando.adapter.PlaceAdapter;
import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.model.WishedRoute;
import mx.ipn.tworisteando.task.NearByPlaceListLoader;
import mx.ipn.tworisteando.task.PlaceListLoader;
import mx.ipn.tworisteando.util.GooglePlayUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

public class RecommendedPlacesActivity extends Activity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {
	public final static String WISHED_ROUTE = "mx.ipn.tworisteando.WISHED_ROUTE";
	private PlaceAdapter placeAdapter;
	private Place destination;
	private String enteredPlace;
	private Map<String, Place> checkedItems;
	LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommended_places);
		// Show the Up button in the action bar.
		setupActionBar();
		enteredPlace = getIntent().getStringExtra(MainActivity.ENTERED_PLACE);
		destination = (Place) getIntent().getSerializableExtra(ConfirmPlaceActivity.DESTINATION_PLACE);
		checkedItems = new HashMap<String, Place>();
		GooglePlayUtil.servicesConnected(this);
		locationClient = new LocationClient(this, this, this);
		searchRecommendedPlaces();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommended_places, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			Intent intent = NavUtils.getParentActivityIntent(this);
			intent.putExtra(MainActivity.ENTERED_PLACE, enteredPlace);
			intent.putExtra(ConfirmPlaceActivity.DESTINATION_PLACE, destination);
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void searchRecommendedPlaces() {
		// Use Google Place Nearby to get recommended places near the destination.
		ListView listView = (ListView) findViewById(R.id.recommendedPlacesList);
		placeAdapter = new PlaceAdapter(this, R.layout.place_list_multiple_choice);
		listView.setAdapter(placeAdapter);
		// Load the list asynchronously.
		// TODO use localization service to dynamically get the locale.
		PlaceListLoader placeListLoader = new NearByPlaceListLoader(placeAdapter, destination.getCoordinates(), new Locale("es", "MX"), null);
		placeListLoader.execute();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		    	onListItemClick(parent, v, position, id);
		    }
	    });
	}
	
	public void onGoButtonClick(View view) {
    	processRoute();
    }
	
	private void onListItemClick(AdapterView<?> parent, View v, int position, long id) {
		CheckedTextView checkedTextView = (CheckedTextView) v.findViewById(R.id.title);
		checkedTextView.toggle();
		// Manage the selected recommended places with a Map.
		Place place = placeAdapter.getItem(position);
		if(checkedItems.containsKey(place.getName()))
			checkedItems.remove(place.getName());
		else
			checkedItems.put(place.getName(), place);
	}
	
	private void processRoute() {
		// Get the user location.
		if(!locationClient.isConnected()) {
			onUserLocationNotAvailable();
		}
		Location location = locationClient.getLastLocation();
		if(location == null)
			onUserLocationNotAvailable();
		// Build a WishRoute object.
		WishedRoute wishedRoute = new WishedRoute();
		wishedRoute.setMainDestination(destination);
		wishedRoute.setSecondaryDestinations(new ArrayList<Place>(checkedItems.values()));
		wishedRoute.setStartLocation(new Coordinates(location.getLatitude(), location.getLongitude()));
		// Go to the next activity.
		Intent intent = new Intent(this, ProcessRouteActivity.class);
    	intent.putExtra(ConfirmPlaceActivity.DESTINATION_PLACE, destination);
    	intent.putExtra(MainActivity.ENTERED_PLACE, enteredPlace);
    	intent.putExtra(WISHED_ROUTE, wishedRoute);
        startActivity(intent);
	}
	
	private void onUserLocationNotAvailable() {
		new AlertDialog.Builder(this).setTitle("Location not available...")
        .setMessage("The application can't retrive your location using the phone location service")
        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	/* TODO If it isn't possible to get the user location via Google Play Services,
            		 * go to another screen allowing the user to enter his departure location.
            		 */
                }
		}).show();
	}
	
	/*
	 * GOOGLE LOCATION SERVICE.
	 */
	@Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        locationClient.connect();
    }
	
	@Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
		locationClient.disconnect();
        super.onStop();
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Decide what to do based on the original request code
		switch (requestCode) {
		case GooglePlayUtil.CONNECTION_FAILURE_RESOLUTION_REQUEST:
			/*
			 * If the result code is Activity.RESULT_OK, try to connect again
			 */
			switch (resultCode) {
			case Activity.RESULT_OK:
				GooglePlayUtil.servicesConnected(this);
				break;
			}
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        GooglePlayUtil.CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
                onUserLocationNotAvailable();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            onUserLocationNotAvailable();
        }

	}
	
	@Override
	public void onConnected(Bundle arg0) {
//        Toast.makeText(this, "GPS connected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisconnected() {
        Toast.makeText(this, "GPS disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
	}
}
