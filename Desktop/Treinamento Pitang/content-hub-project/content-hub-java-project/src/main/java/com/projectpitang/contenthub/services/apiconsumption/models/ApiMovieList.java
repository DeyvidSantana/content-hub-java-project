package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.ArrayList;
import java.util.List;

public class ApiMovieList extends ApiProgramList {

    private List<ApiMovie> results = new ArrayList<ApiMovie>();

    public List<ApiMovie> getResults() {
        return results;
    }

    public void setResults(List<ApiMovie> results) {
        this.results = results;
    }
}
