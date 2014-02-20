package mx.ipn.tworisteando.task;

import mx.ipn.tworisteando.adapter.PlaceAdapter;
import mx.ipn.tworisteando.model.Place;
import android.os.AsyncTask;

public abstract class PlaceListLoader extends AsyncTask<Void, Void, Place[]> {
	private PlaceAdapter placeAdapter;
	protected static final String PROPERTY_TYPES = "google.api.place.search.parameter.types";
	
	public PlaceListLoader(PlaceAdapter placeAdapter) {
		this.placeAdapter = placeAdapter;
	}

	@Override
	protected Place[] doInBackground(Void... params) {
		return getPlaces();
	}
	
	protected abstract Place[] getPlaces();
	
	@Override
	protected void onPostExecute(Place[] result) {
        placeAdapter.updatePlaces(result);
    }
}
