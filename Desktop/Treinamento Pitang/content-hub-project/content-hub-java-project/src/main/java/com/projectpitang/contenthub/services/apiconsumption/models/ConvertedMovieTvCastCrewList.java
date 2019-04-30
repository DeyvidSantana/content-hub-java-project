package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.List;

public class ConvertedMovieTvCastCrewList {

    private Long id;
    private List<ConvertedCast> cast;
    private List<ConvertedCrew> crew;

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

    public List<ConvertedCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<ConvertedCrew> crew) {
        this.crew = crew;
    }
}
