package com.acronym.ore.config;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser<T> {

	public File fileToParse;
	public Class<T> type;

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final JsonParser parser = new JsonParser();

	private JsonObject root;

	public JSONParser(File file, Class<T> objClass) {
		type = objClass;
		fileToParse = file;

		try {
			root = parser.parse(new FileReader(file)).getAsJsonObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<T> getElements(String key) {
		List<T> returnList = new ArrayList<T>();
		if (root.get(key) != null) {
			JsonArray elements = root.get(key).getAsJsonArray();
			for (JsonElement elem : elements) {
				if (type != null && gson.fromJson(elem, type)!=null) {
					returnList.add(gson.fromJson(elem, type));
				}
			}
		}
		return returnList;
	}

}
