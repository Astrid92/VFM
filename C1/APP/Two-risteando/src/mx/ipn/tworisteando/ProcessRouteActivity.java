package mx.ipn.tworisteando;

import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.model.ProcessedRoute;
import mx.ipn.tworisteando.model.WishedRoute;
import mx.ipn.tworisteando.services.LocalRouteProcessor;
import mx.ipn.tworisteando.services.LocalTransportFacilityService;
import mx.ipn.tworisteando.services.RouteProcessor;
import mx.ipn.tworisteando.services.TransportFacilityService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class ProcessRouteActivity extends Activity {
	private Place destination;
	private String enteredPlace;
	private WishedRoute wishedRoute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process_route);
		// Show the Up button in the action bar.
		setupActionBar();
		enteredPlace = getIntent().getStringExtra(MainActivity.ENTERED_PLACE);
		destination = (Place) getIntent().getSerializableExtra(ConfirmPlaceActivity.DESTINATION_PLACE);
		wishedRoute = (WishedRoute) getIntent().getSerializableExtra(RecommendedPlacesActivity.WISHED_ROUTE);
		processRoute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.process_route, menu);
		return true;
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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

	private void processRoute() {
		// TODO
		new AlertDialog.Builder(this).setTitle("Processing route to...")
        .setMessage(wishedRoute.getMainDestination().getDescription())
        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
		}).show();
		TransportFacilityService transportFacilityService = new LocalTransportFacilityService();
		RouteProcessor routeProcessor = new LocalRouteProcessor(transportFacilityService);
		ProcessedRoute processedRoute = routeProcessor.process(wishedRoute);
	}
}
