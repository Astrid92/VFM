package mx.ipn.tworisteando.model;

import java.io.Serializable;
import java.util.List;

public class Line implements Serializable
{
	private static final long serialVersionUID = 7782511742501711948L;
	private String number;
	private LineColor color;
	private List<Station> stations;
	private TransportFacility fromTransportFacility;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public LineColor getColor() {
		return color;
	}
	public void setColor(LineColor color) {
		this.color = color;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public TransportFacility getFromTransportFacility() {
		return fromTransportFacility;
	}
	public void setFromTransportFacility(TransportFacility fromTransportFacility) {
		this.fromTransportFacility = fromTransportFacility;
	}
}
