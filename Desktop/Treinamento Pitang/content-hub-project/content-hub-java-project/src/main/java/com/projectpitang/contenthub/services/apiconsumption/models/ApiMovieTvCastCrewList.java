package com.projectpitang.contenthub.services.apiconsumption.models;

import java.util.List;

public class ApiMovieTvCastCrewList {

    private Long id;
    private List<ApiCastCrew> cast;
    private List<ApiCastCrew> crew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ApiCastCrew> getCast() {
        return cast;
    }

    public void setCast(List<ApiCastCrew> cast) {
        this.cast = cast;
    }

    public List<ApiCastCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<ApiCastCrew> crew) {
        this.crew = crew;
    }
}
