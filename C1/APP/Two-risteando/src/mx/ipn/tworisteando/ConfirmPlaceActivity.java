package mx.ipn.tworisteando;

import java.util.Locale;

import mx.ipn.tworisteando.adapter.PlaceAdapter;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.task.PlaceListLoader;
import mx.ipn.tworisteando.task.SearchByTextPlaceListLoader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ConfirmPlaceActivity extends Activity {
	public final static String DESTINATION_PLACE = "mx.ipn.tworisteando.DESTINATION_PLACE";
	private PlaceAdapter placeAdapter;
	private String enteredPlace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_place);
		// Show the Up button in the action bar.
		setupActionBar();
		enteredPlace = getIntent().getStringExtra(MainActivity.ENTERED_PLACE);
		searchPlaces();
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
		getMenuInflater().inflate(R.menu.confirm_place, menu);
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
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void searchPlaces() {
		/* Use Google Place Text Search to get all corresponding
		 * places in Mexico City.
		 * TODO If there is no result, show a message or show an infobox and regress to the previous screen.
		 * If there is only one result, pass directly to the screen of the recommended places or see the detail to confirm?
		 * If there is more than one result, show the places between which to select one.
		 */
		ListView listView = (ListView) findViewById(R.id.confirmPlaceList);
		placeAdapter = new PlaceAdapter(this, R.layout.place_list_select_one);
		listView.setAdapter(placeAdapter);
		// Load the list asynchronously.
		// TODO use localization service to dynamically get the locale.
		PlaceListLoader placeListLoader = new SearchByTextPlaceListLoader(placeAdapter, enteredPlace, new Locale("es", "MX"));
		placeListLoader.execute();
		listView.setOnItemClickListener(new OnItemClickListener() {
		    @SuppressWarnings("rawtypes")
			public void onItemClick(AdapterView parent, View v, int position, long id) {
		    	onListItemClick(parent, v, position, id);
		    }
	    });
	}
	
    @SuppressWarnings("rawtypes")
	private void onListItemClick(AdapterView parent, View v, int position, long id) {
    	// Go to the next screen (recommended places).
    	Place placeClicked = placeAdapter.getItem(position);
    	Intent intent = new Intent(this, RecommendedPlacesActivity.class);
    	intent.putExtra(DESTINATION_PLACE, placeClicked);
    	intent.putExtra(MainActivity.ENTERED_PLACE, enteredPlace);
        startActivity(intent);
    }
}
