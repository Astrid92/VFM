package mx.ipn.tworisteando.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

public class HttpUtil {
	private HttpUtil() {}
	
	public static String requestForJson(String url) throws IOException {
			DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost(url);
			// Depends on your web service.
			httppost.setHeader("Content-type", "application/json");
	
			InputStream inputStream = null;
		try {
		    HttpResponse response = httpclient.execute(httppost);           
		    HttpEntity entity = response.getEntity();
		    inputStream = entity.getContent();
		    // Json is UTF-8 by default.
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line + "\n");
		    }
		    return sb.toString();
		} catch(IOException e) {
			throw e;
		}
	    finally {
		    try{
		    	if(inputStream != null) inputStream.close();
	    	} catch(IOException e) {}
		}
	}
	
	public static String encode(String input) {
		try {
			return URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
    }
}
