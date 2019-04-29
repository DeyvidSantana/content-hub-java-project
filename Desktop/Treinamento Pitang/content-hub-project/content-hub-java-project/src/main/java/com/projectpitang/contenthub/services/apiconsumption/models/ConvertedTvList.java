package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.ArrayList;
import java.util.List;

public class ConvertedTvList extends ConvertedProgramList {

    private List<ConvertedTv> results = new ArrayList<ConvertedTv>();

    public List<ConvertedTv> getResults() {
        return results;
    }

    public void setResults(List<ConvertedTv> results) {
        this.results = results;
    }

}
