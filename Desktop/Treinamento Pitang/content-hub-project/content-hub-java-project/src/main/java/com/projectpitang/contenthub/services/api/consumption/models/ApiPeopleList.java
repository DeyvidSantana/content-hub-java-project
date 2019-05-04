package com.projectpitang.contenthub.services.api.consumption.models;

import java.util.ArrayList;
import java.util.List;

public class ApiPeopleList {

    private List<ApiPerson> convertedPersonList = new ArrayList<>();

    public List<ApiPerson> getConvertedPersonList() {
        return convertedPersonList;
    }

    public void setConvertedPersonList(List<ApiPerson> convertedPersonList) {
        this.convertedPersonList = convertedPersonList;
    }
}
