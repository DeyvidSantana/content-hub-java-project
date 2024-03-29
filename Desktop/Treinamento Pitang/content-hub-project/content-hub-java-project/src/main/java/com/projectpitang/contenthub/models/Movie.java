package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.dto.MovieDTO;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_movie")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class Movie extends Program{

    public MovieDTO transformToMovieDTO(){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(this.getId());
        movieDTO.setTitle(this.getTitle());
        movieDTO.setOverview(this.getOverview());
        movieDTO.setOriginCountry(this.getOriginCountry());
        movieDTO.setLanguage(this.getLanguage());
        movieDTO.setReleaseDate(this.getReleaseDate());
        movieDTO.setRuntime(this.getRuntime());
        movieDTO.setBackdropPath(this.getBackdropPath());

        List<String> genreDTO = new ArrayList<>();
        for (Genre genre : this.getGenres()) {
            genreDTO.add(genre.getName());
        }
        movieDTO.setGenres(genreDTO);

        List<String> castDTO = new ArrayList<>();
        for (Person person: this.getCast().getCast()) {
            castDTO.add(person.getProfilePath());
        }
        movieDTO.setCast(castDTO);

        return movieDTO;
    }
    
}
