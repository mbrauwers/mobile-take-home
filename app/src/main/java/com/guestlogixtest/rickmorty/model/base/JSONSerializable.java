package com.guestlogixtest.rickmorty.model.base;

public interface JSONSerializable {

    void fromJSON(String jsonStr);
    String toJSON();

}
