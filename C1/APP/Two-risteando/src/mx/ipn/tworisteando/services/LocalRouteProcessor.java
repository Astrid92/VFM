package mx.ipn.tworisteando.services;

import java.util.List;

import mx.ipn.tworisteando.model.ProcessedRoute;
import mx.ipn.tworisteando.model.TransportFacility;
import mx.ipn.tworisteando.model.WishedRoute;

public class LocalRouteProcessor implements RouteProcessor {
	private TransportFacilityService transportFacilityService;

	public LocalRouteProcessor(TransportFacilityService transportFacilityService) {
		this.transportFacilityService = transportFacilityService;
	}

	@Override
	public ProcessedRoute process(WishedRoute wishedRoute) {
		List<TransportFacility> transportFacilities = transportFacilityService.getAvailableTransportFacilities();
		// TODO Auto-generated method stub
		return null;
	}

}
