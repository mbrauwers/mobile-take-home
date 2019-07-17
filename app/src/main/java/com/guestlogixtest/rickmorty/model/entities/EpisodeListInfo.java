package com.guestlogixtest.rickmorty.model.entities;

/*
 * Entity that represents the list info entity from the /episodes RickMorty API call
 */

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import org.json.JSONException;
import org.json.JSONObject;

public class EpisodeListInfo implements JSONSerializable {

    public Integer count;
    public Integer pages;
    public String next;
    public String prev;

    @Override
    public void fromJSON(JSONObject responseJSON) throws JSONException {
        Log.d("msg", "EpisodeListInfo::fromJSON " + responseJSON);
        count = responseJSON.getInt("count");
        pages = responseJSON.getInt("pages");
        next = responseJSON.getString("next");
        prev = responseJSON.getString("prev");
        Log.d("msg", "EpisodeListInfo::fromJSON end");
    }

    @Override
    public String toJSON() {
        return null;
    }
}
