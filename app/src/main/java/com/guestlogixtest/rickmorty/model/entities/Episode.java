package com.guestlogixtest.rickmorty.model.entities;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import java.util.Date;
import java.util.List;

/*
 * Models the RickMorty Episode entity
 */
public class Episode implements JSONSerializable {

    public Integer id;
    public String name;
    public Date airDate;
    public String episode;
    public String url;
    public Date created;
    public List<String> characters;

    @Override
    public void fromJSON(String jsonStr) {

    }

    @Override
    public String toJSON() {
        return null;
    }
}
