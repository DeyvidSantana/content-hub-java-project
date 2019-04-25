package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;

@Entity
@Table(name = "tb_program")
@Inheritance(strategy = InheritanceType.JOINED)
public class Program implements IObjectPersistent<Long> {

    @Id
    @Column(name = "prog_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "prog_cl_title")
    private String title;

    @Column(name = "prog_cl_overview")
    private String overview;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Cast.class)
    @JoinColumn(name="cast_cl_id")
    private Cast cast;

    @Column(name = "prog_cl_prodcountry")
    private String productionCountry;

    @Column(name = "prog_cl_spokenlang")
    private String spokenLanguage;

    @Column(name = "prog_cl_releaseyear")
    private String releaseYear;

    @Column(name = "prog_cl_runtime")
    private int runtime;

    @Column(name = "prog_cl_genres")
    private String programGenres;


}
