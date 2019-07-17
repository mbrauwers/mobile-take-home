package com.guestlogixtest.rickmorty.model.entities;

import android.util.Log;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Entity that represents the /episodes response from the RickMorty API
 */

public class EpisodeListResponse implements JSONSerializable {

    public EpisodeListInfo info;
    public List<Episode> result;

    @Override
    public void fromJSON(JSONObject responseJSON) throws JSONException {
        Log.d("msg", "EpisodListResponse " + responseJSON);

        JSONObject infoObj = responseJSON.getJSONObject("info");
        Log.d("msg", "infoObj is " + infoObj);
        if (infoObj != null) {
            info = new EpisodeListInfo();
            info.fromJSON(infoObj);
        }

        Log.d("msg", "After parsing info subobject");

        JSONArray resultArray = responseJSON.getJSONArray("results");

        Log.d("msg", "resultArray is " + resultArray);

        if (resultArray != null) {
            result = new ArrayList<>(resultArray.length());
            for (int i = 0; i < resultArray.length(); ++i) {
                Episode episode = new Episode();
                episode.fromJSON(resultArray.getJSONObject(i));
                result.add(episode);
            }
        }

    }

    @Override
    public String toJSON() {
        return null;
    }
}
