package mx.ipn.tworisteando.model;

import java.io.Serializable;

// TODO Use Parcelable instead of Serializable (more efficient, see http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents)
public class Place implements Serializable {
	private static final long serialVersionUID = 4686153386268325162L;
	private String description;
	private String name;
	private Coordinates coordinates;
	private PlaceEnrichedInformation enrichedInformation;
	
	public Place() {}
	
	public Place(String description, Coordinates coordinates) {
		this.description = description;
		this.coordinates = coordinates;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public String toString() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlaceEnrichedInformation getEnrichedInformation() {
		return enrichedInformation;
	}

	public void setEnrichedInformation(PlaceEnrichedInformation enrichedInformation) {
		this.enrichedInformation = enrichedInformation;
	}
}
