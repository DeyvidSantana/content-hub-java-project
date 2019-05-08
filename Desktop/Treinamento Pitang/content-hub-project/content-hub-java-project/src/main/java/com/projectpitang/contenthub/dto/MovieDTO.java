package com.projectpitang.contenthub.dto;

import com.projectpitang.contenthub.models.Movie;

public class MovieDTO extends ProgramDTO {

    public Movie transformToMovie(){
        Movie movie = new Movie();
        movie.setTitle(this.getTitle());
        movie.setOverview(this.getOverview());
        movie.setOriginCountry(this.getOriginCountry());
        movie.setLanguage(this.getLanguage());
        movie.setReleaseDate(this.getReleaseDate());
        movie.setRuntime(this.getRuntime());
        movie.setBackdropPath(this.getBackdropPath());

        return movie;
    }

}
