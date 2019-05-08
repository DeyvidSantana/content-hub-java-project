package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.dto.TvDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tv")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class TV extends Program{

    @Column(name = "tv_cl_seansons")
    private int seansons;

    public int getSeansons() {
        return seansons;
    }

    public void setSeansons(int seansons) {
        this.seansons = seansons;
    }

    public TvDTO transformToTvDTO(){
        TvDTO tvDTO = new TvDTO();
        tvDTO.setTitle(this.getTitle());
        tvDTO.setOverview(this.getOverview());
        tvDTO.setOriginCountry(this.getOriginCountry());
        tvDTO.setLanguage(this.getLanguage());
        tvDTO.setReleaseYear(this.getReleaseYear());
        tvDTO.setRuntime(this.getRuntime());
        tvDTO.setSeansons(this.seansons);
        tvDTO.setBackdropPath(this.getBackdropPath());

        List<String> genreDTO = new ArrayList<>();
        for (Genre genre : this.getGenres()) {
            genreDTO.add(genre.getName());
        }
        tvDTO.setGenres(genreDTO);

        List<String> castDTO = new ArrayList<>();
        for (Person person: this.getCast().getCast()) {
            castDTO.add(person.getName());
        }
        tvDTO.setCast(castDTO);

        return tvDTO;
    }
}
