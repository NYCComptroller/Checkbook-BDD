package io.reisys.checkbook.bdd.utilities;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Utilities {
	public static List<String> convertJsonArrayToList(JsonArray jsonArray) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();
		return gson.fromJson(jsonArray, type);
	}

	public static String getStringValueFromJsonObject(JsonObject jsonObject, String key) {
		if (jsonObject.has(key) && !jsonObject.get(key).isJsonNull() && jsonObject.get(key) != null) {
			return jsonObject.get(key).getAsString();
		}
		return null;
	}

	public static JsonArray getJsonArrayFromJsonObject(JsonObject jsonObject, String key) {
		if (jsonObject.has(key) && !jsonObject.get(key).isJsonNull() && jsonObject.get(key) != null) {
			return jsonObject.get(key).getAsJsonArray();
		}

		return null;
	}

	public static JsonObject getJsonObjectFromJsonObject(JsonObject jsonObject, String key) {
		if (jsonObject.has(key) && !jsonObject.get(key).isJsonNull() && jsonObject.get(key) != null) {
			return jsonObject.get(key).getAsJsonObject();
		}
		return null;
	}

	public static String dateConverterToFormat(String month, String year, String dateOfMonth, String format) throws ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMddyyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat(format);
		String dateText = month + dateOfMonth + year;
		Date date = formatter1.parse(dateText);

		return formatter2.format(date);
	}

    public static int getTotalCountFromText(String text) {
		String[] splitted = text.split("\\s+");
		return Integer.parseInt(splitted[splitted.length-1]);
    }
}
