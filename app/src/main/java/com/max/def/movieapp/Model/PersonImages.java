package com.max.def.movieapp.Model;

import java.util.List;

public class PersonImages
{
    private List<PersonImagesProfiles> profiles;

    private Integer id;

    public PersonImages()
    {
    }

    public PersonImages(List<PersonImagesProfiles> profiles, Integer id) {
        this.profiles = profiles;
        this.id = id;

    }

    public List<PersonImagesProfiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<PersonImagesProfiles> profiles) {
        this.profiles = profiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
