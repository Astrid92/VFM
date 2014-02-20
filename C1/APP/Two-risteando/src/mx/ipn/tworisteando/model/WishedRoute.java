package mx.ipn.tworisteando.model;

import java.io.Serializable;
import java.util.List;

public class WishedRoute implements Serializable {
	private static final long serialVersionUID = 8609871879758984951L;
	private Coordinates startLocation;
	private Place mainDestination;
	private List<Place> secondaryDestinations;
	
	public Place getMainDestination() {
		return mainDestination;
	}
	
	public void setMainDestination(Place mainDestination) {
		this.mainDestination = mainDestination;
	}
	
	public List<Place> getSecondaryDestinations() {
		return secondaryDestinations;
	}
	
	public void setSecondaryDestinations(List<Place> secondaryDestinations) {
		this.secondaryDestinations = secondaryDestinations;
	}

	public Coordinates getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Coordinates startLocation) {
		this.startLocation = startLocation;
	}
}
