package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;

import org.json.simple.JSONObject;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import User.User;

/**
 * GoogleMap class
 * 
 *
 */
public class GoogleMap extends JFrame{
	Browser browser;
	BrowserView browserView;
	JFrame jFrame;
	ArrayList<JSONObject> result;

	/**
	 *GoogleMap constructor
	 * 
	 * @param user, value
	 */
	public GoogleMap(User user, ArrayList<JSONObject> value) {
		//pass JSON format value as search result
		result = value;
		jFrame = new JFrame("GoogleMap");
		jFrame.setLayout(new BorderLayout());
		jFrame.setSize(new Dimension(800, 800));
		
		//set a browser to open google map
		browser = new Browser();
		browserView = new BrowserView(browser);
		browserView.setSize(new Dimension(800, 800));
		File file = new File("");
		String path;
		path = file.getAbsolutePath();
		browser.loadURL("file://" + path + "/map.html");
	
		//add browser to jFrame
		jFrame.add(browserView);
		jFrame.setVisible(true);
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//mark all locations in the result to google map
		setMarkers();
		
	}
	
	private String getLocation(JSONObject jsonObject){
		String longitude;	
		String latitude;
		
		//get longtitude and latitude
		latitude = ((JSONObject)((JSONObject)jsonObject.get("location")).get("coordinate")).get("latitude").toString();
		longitude = ((JSONObject)((JSONObject)jsonObject.get("location")).get("coordinate")).get("longitude").toString();
		
		return latitude + ',' + longitude;
	}
	
	private void setMarkers(){
		if (result == null){
			return;
		}
		//set the initial map center to the first location in the search result
		String setCenter = "var mapOptions = {" + 
		         "center: new google.maps.LatLng(" + getLocation(result.get(0))+ ")," + 
		         "zoom: 10" +
		       "};" + 
		       "map = new google.maps.Map(document.getElementById(\"map-canvas\"), mapOptions);";
		browser.executeJavaScript(setCenter);
		
		//mark all locations in the result to google map
		for (JSONObject jsonObject : result){

			String javaScript = "var myLatLng = new google.maps.LatLng(" + getLocation(jsonObject) + ");\n"
	                + "var marker = new google.maps.Marker({\n"+
	                "position: myLatLng,\n"+
	                "map: map\n,"+              
	              "});\n"+
	              "marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');\n"+
	              "markers.push(marker);\n";
			browser.executeJavaScript(javaScript);

		}
	}
	
}
