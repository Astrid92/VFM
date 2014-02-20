package mx.ipn.tworisteando.model;

import java.io.Serializable;

public class RouteFragment implements Serializable {
	private static final long serialVersionUID = 5754292154181456788L;
	private Station[] stations;
	private TransportFacility type;
	
	public Station[] getStations() {
		return stations;
	}
	
	public void setStations(Station[] stations) {
		this.stations = stations;
	}
	
	public TransportFacility getType() {
		return type;
	}
	
	public void setType(TransportFacility type) {
		this.type = type;
	}
}
