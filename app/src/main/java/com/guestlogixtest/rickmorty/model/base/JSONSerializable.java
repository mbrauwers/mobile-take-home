package com.guestlogixtest.rickmorty.model.entities;

interface JSONSerializable {

    void fromJSON(String jsonStr);
    String toJSON();

}
