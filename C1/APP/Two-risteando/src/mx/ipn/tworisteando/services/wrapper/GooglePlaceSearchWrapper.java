package mx.ipn.tworisteando.services.wrapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;
import mx.ipn.tworisteando.model.PlaceEnrichedInformation;
import mx.ipn.tworisteando.services.PlaceSearchService;
import mx.ipn.tworisteando.services.ServiceException;
import mx.ipn.tworisteando.util.HttpUtil;
import mx.ipn.tworisteando.util.PropertyUtil;

public class GooglePlaceSearchWrapper implements PlaceSearchService {

	private static final String PIPE_CHARACTER_URL_ENCODED = "%7C";

	@Override
	public Place[] searchByText(String text, Coordinates location, Integer radius, String[] types, Locale language) throws ServiceException {
		StringBuilder url = new StringBuilder();
		url.append(PropertyUtil.getAppProperties().getProperty("google.api.place.textsearch.url"));
		url.append("json?query=").append(HttpUtil.encode(text));
		url.append("&sensor=false");
		url.append("&key=").append(PropertyUtil.getAppProperties().getProperty("google.api.server.key"));
		if(location != null && radius != null) {
			url.append("&location=").append(location.getLatitude()).append(",").append(location.getLongitude());
			url.append("&radius=").append(radius);
		}
		if(types != null) {
			url.append("&types=");
			for(int i = 0; i < types.length; i++) {
				url.append(types[i]);
				if(i < types.length-1)
					url.append(PIPE_CHARACTER_URL_ENCODED);
			}
		}
		if(language != null)
			url.append("&language=").append(language);
		return getPlacesFromUrl(url.toString());
	}

	@Override
	public Place[] searchNearRankByProminence(Coordinates location, Integer radius, String[] types, Locale language, String filterKeyword) throws ServiceException {
		StringBuilder url = new StringBuilder();
		url.append(PropertyUtil.getAppProperties().getProperty("google.api.place.nearbysearch.url"));
		url.append("json?sensor=false");
		url.append("&rankby=prominence");
		url.append("&key=").append(PropertyUtil.getAppProperties().getProperty("google.api.server.key"));
		url.append("&location=").append(location.getLatitude()).append(",").append(location.getLongitude());
		url.append("&radius=").append(radius);
		if(types != null) {
			url.append("&types");
			for(int i = 0; i < types.length; i++) {
				url.append(types[i]);
				if(i < types.length-1)
					url.append(PIPE_CHARACTER_URL_ENCODED);
			}
		}
		if(language != null)
			url.append("&language=").append(language);
		return getPlacesFromUrl(url.toString());
	}

	@Override
	public Place[] searchNearRankByDistance(Coordinates location, String[] types, Locale language, String filterKeyword) throws ServiceException {
		StringBuilder url = new StringBuilder();
		url.append(PropertyUtil.getAppProperties().getProperty("google.api.place.nearbysearch.url"));
		url.append("json?sensor=false");
		url.append("&rankby=distance");
		url.append("&key=").append(PropertyUtil.getAppProperties().getProperty("google.api.server.key"));
		url.append("&location=").append(location.getLatitude()).append(",").append(location.getLongitude());
		if(types != null) {
			url.append("&types");
			for(int i = 0; i < types.length; i++) {
				url.append(types[i]);
				if(i < types.length-1)
					url.append(PIPE_CHARACTER_URL_ENCODED);
			}
		}
		if(language != null)
			url.append("&language=").append(language);
		return getPlacesFromUrl(url.toString());
	}
	
	private static Place[] getPlacesFromUrl(String url) throws ServiceException {
		Place[] places = null;
		try {
			JSONObject json = new JSONObject(HttpUtil.requestForJson(url));
			// Parse and get places.
			if(!"OK".equals(json.getString("status")))
				throw new ServiceException("Response not OK");
			JSONArray results = json.getJSONArray("results");
			places = new Place[results.length()];
			for (int i=0; i < results.length(); i++) {
				JSONObject result = results.getJSONObject(i);
				places[i] = mapResultJsonToPlace(result);
			}
		} catch (JSONException e) {
			throw new ServiceException("Failed to parse Json", e);
		} catch (IOException e) { 
		    throw new ServiceException("Failed to fetch response", e);
		}
		return places;
	}
	
	private static Place mapResultJsonToPlace(JSONObject json) throws JSONException {
		Place place = new Place();
		// Could be "formatted_address" or "vicinity".
		if(!json.isNull("formatted_address"))
			place.setDescription(json.getString("formatted_address"));
		else
			place.setDescription(json.getString("vicinity"));
		place.setCoordinates(mapGeometryToCoordinates(json.getJSONObject("geometry")));
		place.setName(json.getString("name"));
		PlaceEnrichedInformation enrichedInformation = new PlaceEnrichedInformation();
		try {
			enrichedInformation.setIconUrl(new URL(json.getString("icon")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// Could be null.
		if(!json.isNull("rating"))
			enrichedInformation.setRating(Float.valueOf(json.getString("rating")));
		enrichedInformation.setReference(json.getString("reference"));
		enrichedInformation.setTypes(mapTypesToTypes(json.getJSONArray("types")));
		place.setEnrichedInformation(enrichedInformation);
		return place;
	}
	
	private static Coordinates mapGeometryToCoordinates(JSONObject json) throws JSONException {
		JSONObject jsonLocation = json.getJSONObject("location");
		return new Coordinates(Double.valueOf(jsonLocation.getString("lat")), Double.valueOf(jsonLocation.getString("lng")));
	}
	
	private static String[] mapTypesToTypes(JSONArray typesAsJson) throws JSONException {
		String[] types = new String[typesAsJson.length()];
		for (int i=0; i < typesAsJson.length(); i++)
			types[i] = (String)typesAsJson.get(i);
		return types;
	}
}
