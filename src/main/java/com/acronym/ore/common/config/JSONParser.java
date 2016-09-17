package com.acronym.ore.common.config;

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
			System.out.println("root not null");
			JsonArray elements = root.get(key).getAsJsonArray();
			for (JsonElement elem : elements) {
				System.out.println("elem:" + elem);
				System.out.println(type);
				System.out.println(gson.fromJson(elem, type));
				if (type != null && gson.fromJson(elem, type)!=null) {
					System.out.println("type not null");
					returnList.add(gson.fromJson(elem, type));
				}
			}
		}
		System.out.println("done");
		return returnList;
	}

}
