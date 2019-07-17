package com.guestlogixtest.rickmorty.model.entities;

import java.util.Date;
import java.util.List;

/*
 * Models the RickMorty Episode entity
 */
public class Episode {

    public Integer id;
    public String name;
    public Date airDate;
    public String episode;
    public String url;
    public Date created;
    public List<String> characters;

}
