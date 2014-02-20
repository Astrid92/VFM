package mx.ipn.tworisteando.util;

import android.location.Location;
import mx.ipn.tworisteando.model.Coordinates;

public class GeographyUtil {
	private GeographyUtil() {}
	
	public enum DistanceUnit {
		METER,
		KILOMETER,
		MILE
	}
	
	public static double getDistance(Coordinates start, Coordinates end, DistanceUnit unit) {
		float[] results = null;
		Location.distanceBetween(
				start.getLatitude(), start.getLongitude(),
				end.getLatitude(), end.getLongitude(),
				results
		);
		if(results == null || results.length < 1)
			throw new IllegalArgumentException("Unable to process");
		@SuppressWarnings("unused")
		double result = results[0];
		if(unit == DistanceUnit.METER)
			return result;
		else if(unit == DistanceUnit.KILOMETER)
			return result / 1000;
		else if(unit == DistanceUnit.MILE)
			return convertMetersToMiles(result);
		throw new IllegalArgumentException("Unit not available");
	}
	
	public static double convertMetersToMiles(double meters) {
		return meters * 0.00062137119;
	}
}
