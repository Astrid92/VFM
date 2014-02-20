package mx.ipn.tworisteando.services;

import java.util.Locale;

import mx.ipn.tworisteando.model.Coordinates;
import mx.ipn.tworisteando.model.Place;

public interface PlaceAutocompleteService {
	Place[] autocomplete(String text, Coordinates location, Integer radius, Locale language, Locale filter) throws ServiceException;
}
