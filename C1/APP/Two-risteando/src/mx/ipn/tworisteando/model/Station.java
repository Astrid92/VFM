package mx.ipn.tworisteando.model;

import java.io.Serializable;

public class Station implements Serializable {
	private static final long serialVersionUID = 5090615943652263666L;
	private String name;
	private Coordinates coordinates;
	private Station nextStation;
	private StationType type;
	private Line[] fromLines;
	
	public Line[] getFromLines() {
		return fromLines;
	}
	public void setFromLines(Line[] fromLines) {
		this.fromLines = fromLines;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Station getNextStation() {
		return nextStation;
	}
	public void setNextStation(Station nextStation) {
		this.nextStation = nextStation;
	}
	public StationType getType() {
		return type;
	}
	public void setType(StationType type) {
		this.type = type;
	}
}
