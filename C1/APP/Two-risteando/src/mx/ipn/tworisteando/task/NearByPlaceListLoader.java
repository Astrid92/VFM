package mx.ipn.tworisteando.task;

import java.util.Locale;

import mx.ipn.tworisteando.adapter.PlaceAdapter;
import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.services.PlaceSearchService;
import mx.ipn.tworisteando.services.ServiceException;
import mx.ipn.tworisteando.services.wrapper.GooglePlaceSearchWrapper;
import mx.ipn.tworisteando.util.PropertyUtil;

public class NearByPlaceListLoader extends PlaceListLoader {
	private Coordinates location;
	private Locale language;
	private String filterKeyword;
	private static final String PORPERTY_RADIUS = "google.api.place.nearbysearch.parameter.radius";

	public NearByPlaceListLoader(PlaceAdapter placeAdapter, Coordinates location, Locale language, String filterKeyword) {
		super(placeAdapter);
		this.location = location;
        this.language = language;
        this.filterKeyword = filterKeyword;
	}

	@Override
	protected Place[] getPlaces() {
		Place[] places = null;
		PlaceSearchService placeSearchService = new GooglePlaceSearchWrapper();
		try {
			places = placeSearchService.searchNearRankByProminence(
					location,
					Integer.parseInt(PropertyUtil.getAppProperties().getProperty(PORPERTY_RADIUS)),
					PropertyUtil.getAppProperties().getProperty(PROPERTY_TYPES).split("\\|"),
					language,
					filterKeyword
			);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return places;
	}

}
