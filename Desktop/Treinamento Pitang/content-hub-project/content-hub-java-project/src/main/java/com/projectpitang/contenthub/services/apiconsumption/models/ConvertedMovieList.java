package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.ArrayList;
import java.util.List;

public class ConvertedMovieList extends ConvertedProgramList{

    private List<ConvertedMovie> results = new ArrayList<ConvertedMovie>();

    public List<ConvertedMovie> getResults() {
        return results;
    }

    public void setResults(List<ConvertedMovie> results) {
        this.results = results;
    }
}
