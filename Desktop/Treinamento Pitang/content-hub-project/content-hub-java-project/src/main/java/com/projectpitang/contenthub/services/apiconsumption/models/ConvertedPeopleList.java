package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.ArrayList;
import java.util.List;

public class ConvertedPeopleList {

    private List<ApiPerson> convertedPersonList = new ArrayList<>();

    public List<ApiPerson> getConvertedPersonList() {
        return convertedPersonList;
    }

    public void setConvertedPersonList(List<ApiPerson> convertedPersonList) {
        this.convertedPersonList = convertedPersonList;
    }
}
