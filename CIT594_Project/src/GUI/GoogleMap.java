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

public class GoogleMap extends JFrame{
	Browser browser;
	BrowserView browserView;
	JFrame jFrame;
	ArrayList<JSONObject> result;
	public GoogleMap(User user, ArrayList<JSONObject> value) {
		result = value;
		jFrame = new JFrame("GoogleMap");
		jFrame.setLayout(new BorderLayout());
		jFrame.setSize(new Dimension(800, 800));
		
		browser = new Browser();
		browserView = new BrowserView(browser);
		browserView.setSize(new Dimension(800, 800));
		File file = new File("");
		String path;
		path = file.getAbsolutePath();
		browser.loadURL("file://" + path + "/map.html");
	
		
		jFrame.add(browserView);
		jFrame.setVisible(true);
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getLocation(JSONObject jsonObject){
		String longitude;
		String latitude;
		
		latitude = ((JSONObject)((JSONObject)jsonObject.get("location")).get("coordinate")).get("latitude").toString();
		longitude = ((JSONObject)((JSONObject)jsonObject.get("location")).get("coordinate")).get("longitude").toString();
		
		return latitude + ',' + longitude;
	}
	
	
}