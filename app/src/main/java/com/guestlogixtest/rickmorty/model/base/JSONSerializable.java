package com.guestlogixtest.rickmorty.model.base;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONSerializable {

    void fromJSON(JSONObject object) throws JSONException;
    String toJSON();

}
