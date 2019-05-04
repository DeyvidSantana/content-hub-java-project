package com.projectpitang.contenthub.services.api.consumption.models;

import java.util.ArrayList;
import java.util.List;

public class ApiTvList extends ApiProgramList {

    private List<ApiTv> results = new ArrayList<ApiTv>();

    public List<ApiTv> getResults() {
        return results;
    }

    public void setResults(List<ApiTv> results) {
        this.results = results;
    }

}
