package com.guestlogixtest.rickmorty.model.entities;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/*
 * Models the RickMorty Episode entity
 */
public class Episode implements JSONSerializable {

    public Integer id;
    public String name;
    public String airDate;
    public String episode;
    public String url;
    public String created;
    public List<String> characters;

    @Override
    public void fromJSON(JSONObject responseJSON) throws JSONException {

        id = responseJSON.getInt("id");
        name = responseJSON.getString("name");
        airDate = responseJSON.getString("air_date");
        episode = responseJSON.getString("episode");
        url = responseJSON.getString("url");
        created = responseJSON.getString("created");

        JSONArray resultArray = responseJSON.getJSONArray("characters");
        if (resultArray != null) {
            characters = new ArrayList<>(resultArray.length());
            for (int i = 0; i < resultArray.length(); ++i) {
                characters.add(resultArray.getString(i));
            }
        }

    }

    @Override
    public String toJSON() {
        return null;
    }
}
