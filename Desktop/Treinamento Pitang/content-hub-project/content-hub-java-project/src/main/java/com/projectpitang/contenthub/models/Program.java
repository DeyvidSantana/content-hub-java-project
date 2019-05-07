package com.projectpitang.contenthub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_program")
@JsonIgnoreProperties({"genres"})
@Inheritance(strategy = InheritanceType.JOINED)
public class Program implements IObjectPersistent<Long> {

    @Id
    @Column(name = "prog_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "prog_cl_idapi")
    private Long idApi;

    @Size(min = 1, max = 200)
    @Column(name = "prog_cl_title")
    private String title;

    @Column(name = "prog_cl_overview", length = 2000)
    private String overview;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Cast.class)
    @JoinColumn(name="cast_cl_id")
    private Cast cast;

    @Size(min = 1, max = 50)
    @Column(name = "prog_cl_origincountry")
    private String originCountry;

    @Size(min = 1, max = 20)
    @Column(name = "prog_cl_language")
    private String language;

    //@Temporal(TemporalType.DATE)
    @Column(name = "prog_cl_releaseyear")
    private String releaseYear;

    @Column(name = "prog_cl_runtime")
    private int runtime;

    @Column(name = "prog_cl_posterpath")
    private String backdropPath;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Genre.class)
    @JoinTable(name = "tb_program_genre",
            joinColumns = @JoinColumn(referencedColumnName = "prog_cl_id"),
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "gen_cl_id")})
    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Objects.equal(id, program.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdApi() {
        return idApi;
    }

    public void setIdApi(Long idApi) {
        this.idApi = idApi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
