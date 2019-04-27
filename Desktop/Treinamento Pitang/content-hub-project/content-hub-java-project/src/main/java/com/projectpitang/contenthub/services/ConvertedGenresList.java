package com.projectpitang.contenthub.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertedGenresList {

    private List<ConvertedGenres> genres = new ArrayList<ConvertedGenres>();

    public List<ConvertedGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<ConvertedGenres> genres) {
        this.genres = genres;
    }
}
