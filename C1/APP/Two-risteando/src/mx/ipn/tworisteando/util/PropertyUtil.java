package mx.ipn.tworisteando.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private static final String APP_PROPERTIES_FILE_NAME = "assets/app.properties";
	private static Properties appProperties = null;

	private PropertyUtil() {}
	
	public static Properties getAppProperties() {
		if(appProperties == null) {
			try {
				InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(APP_PROPERTIES_FILE_NAME);
				appProperties = new Properties();
				appProperties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return appProperties;
	}
}
