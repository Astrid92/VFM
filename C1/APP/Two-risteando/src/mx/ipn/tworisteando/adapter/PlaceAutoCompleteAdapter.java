package mx.ipn.tworisteando.adapter;

import java.util.Locale;

import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.services.PlaceAutocompleteService;
import mx.ipn.tworisteando.services.ServiceException;
import mx.ipn.tworisteando.services.wrapper.GooglePlaceAutocompleteWrapper;
import mx.ipn.tworisteando.util.PropertyUtil;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class PlaceAutoCompleteAdapter extends ArrayAdapter<Place> implements Filterable {
	private static final String PROPERTY_COMPONENTS = "google.api.place.autocomplete.parameter.location.components";
	private static final String PORPERTY_RADIUS = "google.api.place.autocomplete.parameter.radius";
	private static final String PROPERTY_LONGITUDE = "google.api.place.autocomplete.parameter.location.longitude";
	private static final String PROPERTY_LATITUDE = "google.api.place.autocomplete.parameter.location.latitude";
	private Place[] places;
	private PlaceAutocompleteService placeAutocompleteService;
	
	public PlaceAutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        places = new Place[0];
        placeAutocompleteService = new GooglePlaceAutocompleteWrapper();
    }
	
	@Override
    public int getCount() {
        return places.length;
    }

    @Override
    public Place getItem(int index) {
        return places[index];
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null) {
                    try {
						places = placeAutocompleteService.autocomplete(
								constraint.toString(),
								new Coordinates(
										Double.parseDouble(PropertyUtil.getAppProperties().getProperty(PROPERTY_LATITUDE)),
										Double.parseDouble(PropertyUtil.getAppProperties().getProperty(PROPERTY_LONGITUDE))
								),
								Integer.parseInt(PropertyUtil.getAppProperties().getProperty(PORPERTY_RADIUS)),
								new Locale("es", "MX"), // TODO use localization service to dynamically get the locale.
								new Locale("es", PropertyUtil.getAppProperties().getProperty(PROPERTY_COMPONENTS))
						);
						filterResults.values = places;
						filterResults.count = places.length;
					} catch (ServiceException e) {
						e.printStackTrace();
					}
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                if(results != null && results.count > 0) {
                	notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }
}
