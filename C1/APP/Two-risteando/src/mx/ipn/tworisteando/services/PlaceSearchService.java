package mx.ipn.tworisteando.services;

import java.util.Locale;

import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;

public interface PlaceSearchService {
	Place[] searchByText(String text, Coordinates location, Integer radius, String[] types, Locale language) throws ServiceException;
	
	Place[] searchNearRankByProminence(Coordinates location, Integer radius, String[] types, Locale language, String filterKeyword) throws ServiceException;
	
	Place[] searchNearRankByDistance(Coordinates location, String[] types, Locale language, String filterKeyword) throws ServiceException;
}
