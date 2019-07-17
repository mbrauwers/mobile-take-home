package com.guestlogixtest.rickmorty.model.entities;

/*
 * Models the RickMorty Character Entity
 */

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import java.util.Date;
import java.util.List;

public class Character implements JSONSerializable {

    public Integer id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Location origin;
    public Location location;
    public String image;
    public List<String> episode;
    public String url;
    public Date created;

    @Override
    public void fromJSON(String jsonStr) {

    }

    @Override
    public String toJSON() {
        return null;
    }
}
