package com.franklin.model.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MapperHelper {
    public static String isJsonValidMember(JsonObject jsonObject,String key){
        if(jsonObject.has(key)){
            return jsonObject.get(key).getAsString();
        }
        return "";
    }

    public static String getJsonObjectString(JsonObject jsonObject,String jsonField){
        try{
            String jsonValue = jsonObject.get(jsonField).getAsString();
            JsonObject body = new JsonParser().parse(jsonValue).getAsJsonObject();
            body.get("typeOfObject").getAsString();
            return jsonObject.get(jsonField).getAsString();
        }catch (Exception ex){
            return jsonObject.get(jsonField).toString();
        }
    }
    //               bodyString = bodyString
    //                       .replace("\\n\\t", "")
    //                       .replace("\\n","")
    //                       .replace("\\","")
    //                       .replace("\"\\{","{")
    //                       .replace("\"\\}","}");
}
