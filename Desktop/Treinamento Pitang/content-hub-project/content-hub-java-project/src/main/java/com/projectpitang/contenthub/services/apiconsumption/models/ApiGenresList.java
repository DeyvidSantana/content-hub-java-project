package com.projectpitang.contenthub.services.apiconsumption.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiGenresList {

    private List<ApiGenres> genres = new ArrayList<ApiGenres>();

    public List<ApiGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<ApiGenres> genres) {
        this.genres = genres;
    }
}
