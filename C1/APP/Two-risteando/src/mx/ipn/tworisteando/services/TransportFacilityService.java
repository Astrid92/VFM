package mx.ipn.tworisteando.services;

import java.util.List;

import mx.ipn.tworisteando.model.TransportFacility;

public interface TransportFacilityService {
	List<TransportFacility> getAvailableTransportFacilities();
}
