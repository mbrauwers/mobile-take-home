package com.guestlogixtest.rickmorty.model.entities;

import com.guestlogixtest.rickmorty.model.base.JSONSerializable;

import java.util.List;

/*
 * Entity that represents the /episodes response from the RickMorty API
 */

public class EpisodeListResponse implements JSONSerializable {

    public EpisodeListInfo info;
    public List<Episode> result;

    @Override
    public void fromJSON(String jsonStr) {

    }

    @Override
    public String toJSON() {
        return null;
    }
}
