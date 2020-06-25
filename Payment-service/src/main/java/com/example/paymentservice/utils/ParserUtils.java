package com.example.paymentservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * JSON to generic object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    /**
     * JSON to list of generic objects
     */
    public <T> List<T> fromJsonAsList(String json, Class<T[]> clazz) {
        return Arrays.asList(new Gson().fromJson(json, clazz));
    }
    public <T> List<T> parseJsonObjectsToList(JSONObject parentJson, String key, Class<T> clazz)
            throws IOException, JSONException {
        Object childObject = parentJson.get(key);
        if (childObject == null) {
            return null;
        }
        if (childObject instanceof JSONArray) {
            JSONArray jsonArray = parentJson.getJSONArray(key);
            return getList(jsonArray.toString(), clazz);
        }
        JSONObject jsonObject = parentJson.getJSONObject(key);
        List<T> jsonList = new ArrayList<>();
        jsonList.add(getObject(jsonObject.toString(), clazz));
        return jsonList;
    }

    public <T> List<T> getList(String jsonStr, Class clazz) throws IOException {
        ObjectMapper objectMapper = OBJECT_MAPPER;
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return objectMapper.readValue(jsonStr, typeFactory.constructCollectionType(List.class, clazz));
    }

    public <T> T getObject(String jsonStr, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = OBJECT_MAPPER;
        return objectMapper.readValue(jsonStr, clazz);
    }
}
