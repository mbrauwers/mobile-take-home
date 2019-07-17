package com.guestlogixtest.rickmorty.model.entities;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Location implements JSONSerializable {
    public String name;
    public String url;

    @Override
    public void fromJSON(JSONObject responseJSON) throws JSONException {
        name = responseJSON.getString("name");
        url = responseJSON.getString("url");
    }

    @Override
    public String toJSON() {
        return null;
    }
}
