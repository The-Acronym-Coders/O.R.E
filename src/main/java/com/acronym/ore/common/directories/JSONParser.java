package com.acronym.ore.common.directories;

import com.acronym.ore.common.helpers.Logger;
import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser<T> {

    public File fileToParse;
    public Class<T> type;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final JsonParser parser = new JsonParser();

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
        List<T> returnList = new ArrayList<>();
        if (root.get(key) != null) {
            Logger.info("root not null");
            JsonArray elements = root.get(key).getAsJsonArray();
            for (JsonElement elem : elements) {
                Logger.info("elem:" + elem);
                Logger.info(type);
                Logger.info(gson.fromJson(elem, type));
                if (type != null && gson.fromJson(elem, type) != null) {
                    Logger.info("type not null");
                    returnList.add(gson.fromJson(elem, type));
                }
            }
        }
        Logger.info("done");
        return returnList;
    }

}