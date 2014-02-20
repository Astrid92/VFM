package mx.ipn.tworisteando.model;

import java.io.Serializable;
import java.util.List;

public class TransportFacility implements Serializable
{
	public enum TransportType {
		METRO,
		METROBUS
	}
	private static final long serialVersionUID = -971936463675271494L;
	private String name;
	private TransportType type;
	private long priceInCents;
	private List<Line> lines;
	
	public TransportType getType() {
		return type;
	}
	public void setType(TransportType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPriceInCents() {
		return priceInCents;
	}
	public void setPriceInCents(long priceInCents) {
		this.priceInCents = priceInCents;
	}
	public List<Line> getLines() {
		return lines;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
}
