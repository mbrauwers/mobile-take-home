package com.guestlogixtest.rickmorty.model.entities;

/*
 * Models the RickMorty Character Entity
 */

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpisodeCharacter implements JSONSerializable {

    public Integer id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Location origin;
    public Location location;
    public String image;
    public List<String> episodes;
    public String url;
    public String created;

    @Override
    public void fromJSON(JSONObject responseJSON) throws JSONException {
        id = responseJSON.getInt("id");
        name = responseJSON.getString("name");
        status = responseJSON.getString("status");
        species = responseJSON.getString("species");
        type = responseJSON.getString("type");
        gender = responseJSON.getString("gender");
        image = responseJSON.getString("image");
        url = responseJSON.getString("url");
        created = responseJSON.getString("created");

        JSONObject originObj = responseJSON.getJSONObject("origin");
        if (originObj != null) {
            origin = new Location();
            origin.fromJSON(originObj);
        }

        JSONObject locationObj = responseJSON.getJSONObject("location");
        if (locationObj != null) {
            location = new Location();
            location.fromJSON(locationObj);
        }

        JSONArray resultArray = responseJSON.getJSONArray("episode");
        if (resultArray != null) {
            episodes = new ArrayList<>(resultArray.length());
            for (int i = 0; i < resultArray.length(); ++i) {
                episodes.add(resultArray.getString(i));
            }
        }

    }

    @Override
    public String toJSON() {
        return null;
    }
}
