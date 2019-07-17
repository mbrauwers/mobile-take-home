package com.guestlogixtest.rickmorty.data.model;

/*
 * Models the RickMorty Character Entity
 */

import java.util.Date;
import java.util.List;

public class Character {

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
}
