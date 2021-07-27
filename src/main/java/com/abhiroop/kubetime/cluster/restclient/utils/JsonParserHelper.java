package com.abhiroop.kubetime.cluster.restclient.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserHelper {

	public static String getDataValue(String jsonString, String[] tagList) throws ParseException {
		String result = null;
		Object o = null;
		if (jsonString != null && tagList != null && tagList.length > 0) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			for (int i = 0; i < tagList.length; i++) {

				o = jo.get(tagList[i]);
				if (o instanceof String) {
					result = (String) o;
					break;
				} else if (o instanceof JSONObject) {
					jo = (JSONObject) o;
				}
			}

			if (result == null) {
				result = jo.toJSONString();
			}
		}
		return result;
	}

}
