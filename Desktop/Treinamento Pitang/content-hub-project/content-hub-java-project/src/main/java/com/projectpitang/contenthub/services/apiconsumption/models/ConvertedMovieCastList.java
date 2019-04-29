package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.List;

public class ConvertedMovieCastList {

    private Long id;
    private List<ConvertedCast> cast;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ConvertedCast> getCast() {
        return cast;
    }

    public void setCast(List<ConvertedCast> cast) {
        this.cast = cast;
    }
}
