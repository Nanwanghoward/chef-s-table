package JSONParse;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class JSONParse {
	public static JSONObject getJSON(String string) {
		JSONParser parser = new JSONParser();
		JSONObject response = null;
		try {
			response = (JSONObject) parser.parse(string);
			return response;
		} catch (ParseException pe) {
			return null;
		}
	}
}
