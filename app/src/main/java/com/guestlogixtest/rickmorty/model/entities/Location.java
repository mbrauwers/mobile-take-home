package com.guestlogixtest.rickmorty.model.entities;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

public class Location implements JSONSerializable {
    public String name;
    public String url;

    @Override
    public void fromJSON(String jsonStr) {

    }

    @Override
    public String toJSON() {
        return null;
    }
}
