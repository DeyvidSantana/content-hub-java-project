package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.dto.MovieDTO;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movie")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class Movie extends Program{

    public MovieDTO transformToMovieDTO(){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(this.getTitle());
        movieDTO.setOverview(this.getOverview());
        movieDTO.setOriginCountry(this.getOriginCountry());
        movieDTO.setLanguage(this.getLanguage());
        movieDTO.setReleaseYear(this.getReleaseYear());
        movieDTO.setRuntime(this.getRuntime());
        movieDTO.setBackdropPath(this.getBackdropPath());

        return movieDTO;
    }
    
}
