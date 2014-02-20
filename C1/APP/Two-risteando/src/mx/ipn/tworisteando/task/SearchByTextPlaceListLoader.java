package mx.ipn.tworisteando.task;

import java.util.Locale;

import mx.ipn.tworisteando.adapter.PlaceAdapter;
import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.services.PlaceSearchService;
import mx.ipn.tworisteando.services.ServiceException;
import mx.ipn.tworisteando.services.wrapper.GooglePlaceSearchWrapper;
import mx.ipn.tworisteando.util.PropertyUtil;

public class SearchByTextPlaceListLoader extends PlaceListLoader {
	private String query;
	private Locale language;
	private static final String PORPERTY_RADIUS = "google.api.place.textsearch.parameter.radius";
	private static final String PROPERTY_LONGITUDE = "google.api.place.textsearch.parameter.location.longitude";
	private static final String PROPERTY_LATITUDE = "google.api.place.textsearch.parameter.location.latitude";

	public SearchByTextPlaceListLoader(PlaceAdapter placeAdapter, String query, Locale language) {
		super(placeAdapter);
		this.query = query;
        this.language = language;
	}

	@Override
	protected Place[] getPlaces() {
		Place[] places = null;
		PlaceSearchService placeSearchService = new GooglePlaceSearchWrapper();
		try {
			places = placeSearchService.searchByText(
					query,
					new Coordinates(
							Double.parseDouble(PropertyUtil.getAppProperties().getProperty(PROPERTY_LATITUDE)),
							Double.parseDouble(PropertyUtil.getAppProperties().getProperty(PROPERTY_LONGITUDE))
					),
					Integer.parseInt(PropertyUtil.getAppProperties().getProperty(PORPERTY_RADIUS)),
					PropertyUtil.getAppProperties().getProperty(PROPERTY_TYPES).split("\\|"),
					language
			);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return places;
	}

}
