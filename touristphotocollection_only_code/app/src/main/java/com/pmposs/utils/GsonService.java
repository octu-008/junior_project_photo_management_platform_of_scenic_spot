package com.pmposs.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.pmposs.model.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class GsonService {
    public User parseUserJSON(String json)
    {
        Gson gson=new Gson();
        User user=gson.fromJson(json,User.class);
        return user;
    }
    public Team parseTeamJSON(String json)
    {
        Gson gson=new Gson();
        Team team=gson.fromJson(json,Team.class);
        return team;
    }
    public List<Spot> parseSpotsJSON(String json)
    {
        Gson gson=new Gson();
        List<Spot> spotList=gson.fromJson(json,new TypeToken<List<Spot>>(){}.getType());
        return spotList;
    }
    public List<Print_style> parsePrintStyleJSON(String json)
    {
        Gson gson=new Gson();
        List<Print_style> print_styles=gson.fromJson(json,new TypeToken<List<Print_style>>(){}.getType());
        return print_styles;
    }
    public List<Photo> parsePhotoJSON(String json)
    {
        GsonBuilder builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();
        List<Photo> photoList=gson.fromJson(json,new TypeToken<List<Photo>>(){}.getType());
        return photoList;
    }
}
