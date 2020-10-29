package io.reisys.checkbook.utilities;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class CommonUtility {

	public static List<String> convertJsonArrayToList(JsonArray jsonArray) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();
		return gson.fromJson(jsonArray, type);
	}
}
