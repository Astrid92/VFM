package mx.ipn.tworisteando.model;

import java.io.Serializable;
import java.net.URL;

//TODO Use Parcelable instead of Serializable (more efficient, see http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents)
public class PlaceEnrichedInformation implements Serializable {
	private static final long serialVersionUID = -5578469072466959179L;
	private URL iconUrl;
	private float rating;
	private String reference;
	private String[] types;
	
	public URL getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(URL iconUrl) {
		this.iconUrl = iconUrl;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
}
