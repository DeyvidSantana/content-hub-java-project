package com.projectpitang.contenthub.dto;

import com.projectpitang.contenthub.models.Person;
import com.projectpitang.contenthub.utils.PersonGender;

public class PersonDTO {

    private String name;
    private Double height;
    private String hometown;
    private String homeCountry;
    private PersonGender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public Person transformToPerson(){
        Person person = new Person();
        person.setName(this.name);
        person.setHeight(this.height);
        person.setHometown(this.hometown);
        person.setHomeCountry(this.homeCountry);
        person.setGender(this.gender);

        return person;
    }
}
