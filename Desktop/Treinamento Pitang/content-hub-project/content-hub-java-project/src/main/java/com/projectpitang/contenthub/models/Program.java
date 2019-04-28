package com.projectpitang.contenthub.models;

import com.google.common.base.Objects;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tb_program")
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

    @Column(name = "prog_cl_overview", length = 100000)
    private String overview;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Cast.class)
    @JoinColumn(name="cast_cl_id")
    private Cast cast;

    @Size(min = 1, max = 30)
    @Column(name = "prog_cl_prodcountry")
    private String productionCountry;

    @Size(min = 1, max = 20)
    @Column(name = "prog_cl_language")
    private String language;

    //@Temporal(TemporalType.DATE)
    @Column(name = "prog_cl_releaseyear")
    private String releaseYear;

    @Column(name = "prog_cl_runtime")
    private int runtime;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Genre.class)
    @JoinTable(name = "tb_program_genre",
            joinColumns = @JoinColumn(referencedColumnName = "prog_cl_id"),
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "gen_cl_id")})
    private Set<Genre> genres;

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

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
