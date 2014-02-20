package mx.ipn.tworisteando.model;

import java.io.Serializable;

//TODO Use Parcelable instead of Serializable (more efficient, see http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents)
public class Coordinates implements Serializable {
	private static final long serialVersionUID = -2906502372767338871L;
	private double latitude;
	private double longitude;
	
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
