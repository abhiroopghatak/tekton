package com.abhiroop.kubetime.cluster.restclient.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserHelper {

	public static JSONArray getItemsFromResource (String jsonString)throws ParseException{
	
		JSONArray ja=null;
		if (jsonString != null) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			if (jo.get("items") != null) {
				ja = (JSONArray) jo.get("items");
			}
		}
			return ja;
	}

	public static String getNameSpacePvc(String jsonString) throws ParseException {
		String val = null;
		if (jsonString != null) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			if (jo.get("items") != null) {
				JSONArray ja = (JSONArray) jo.get("items");
				for (Object o : ja) {

					val = getDataValue(o.toString(), new String[] { "spec", "resources", "requests", "storage" });
				}
			}
		}
		return val;
	}

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

	public static List<String> getNameSpaceNames(String jsonString) throws ParseException {
		List<String> nameList = new ArrayList<>();
		if (jsonString != null) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			if (jo.get("items") != null) {
				JSONArray ja = (JSONArray) jo.get("items");
				for (Object o : ja) {
					nameList.add(getDataValue(o.toString(), new String[] { "metadata", "name" }));
				}
			}
		}

		return nameList;

	}
}
