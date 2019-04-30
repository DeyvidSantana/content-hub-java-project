package com.projectpitang.contenthub.services.apiconsumption.models;

import com.projectpitang.contenthub.utils.PersonGender;

public abstract class ConvertedCastCrew {

    private Long id;
    private String name;
    private PersonGender gender;
    private String profile_path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
