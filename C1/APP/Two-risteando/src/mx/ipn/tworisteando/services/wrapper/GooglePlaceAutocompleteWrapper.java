package mx.ipn.tworisteando.services.wrapper;

import java.io.IOException;
import java.util.Locale;

import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.services.PlaceAutocompleteService;
import mx.ipn.tworisteando.services.ServiceException;
import mx.ipn.tworisteando.util.HttpUtil;
import mx.ipn.tworisteando.util.PropertyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlaceAutocompleteWrapper implements PlaceAutocompleteService {

	@Override
	/**
	 * See https://developers.google.com/places/documentation/autocomplete.
	 */
	public Place[] autocomplete(String input, Coordinates location, Integer radius, Locale language, Locale filterByCountry) throws ServiceException {
		Place[] places = null;
		StringBuilder url = new StringBuilder();
		url.append(PropertyUtil.getAppProperties().getProperty("google.api.place.autocomplete.url"));
		url.append("json?input=").append(HttpUtil.encode(input));
		url.append("&sensor=false");
		url.append("&key=").append(PropertyUtil.getAppProperties().getProperty("google.api.server.key"));
		if(location != null && radius != null) {
			url.append("&location=").append(location.getLatitude()).append(",").append(location.getLongitude());
			url.append("&radius=").append(radius);
		}
		if(language != null)
			url.append("&language=").append(language);
		if(filterByCountry != null)
			url.append("&components").append(filterByCountry.getCountry());
		
		try {
			JSONObject json = new JSONObject(HttpUtil.requestForJson(url.toString()));
			// Parse and get places.
			if(!"OK".equals(json.getString("status")))
				throw new ServiceException("Response not OK");
			JSONArray predictions = json.getJSONArray("predictions");
			places = new Place[predictions.length()];
			for (int i=0; i < predictions.length(); i++) {
				JSONObject prediction = predictions.getJSONObject(i);
				places[i] = mapPredictionJsonToPlace(prediction);
			}
		} catch (JSONException e) {
			throw new ServiceException("Failed to parse Json", e);
		} catch (IOException e) { 
		    throw new ServiceException("Failed to fetch response", e);
		}
		return places;
	}
	
	private static Place mapPredictionJsonToPlace(JSONObject json) throws JSONException {
		Place place = new Place();
		place.setDescription(json.getString("description"));
		return place;
	}
}
