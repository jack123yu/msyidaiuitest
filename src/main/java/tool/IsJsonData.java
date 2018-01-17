package tool;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class IsJsonData {
	public static boolean iIsJsonData(String json){
		try {
			JsonParser parser=new JsonParser();
			parser.parse(json);
			return true;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
		return false;
		}
		
	}

}
