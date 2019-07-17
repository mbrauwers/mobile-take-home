package com.guestlogixtest.rickmorty.model.entities;

/*
 * Entity that represents the list info entity from the /episodes RickMorty API call
 */

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

public class EpisodeListInfo implements JSONSerializable {

    public Integer count;
    public Integer pages;
    public String next;
    public String prev;

    @Override
    public void fromJSON(String jsonStr) {

    }

    @Override
    public String toJSON() {
        return null;
    }
}
