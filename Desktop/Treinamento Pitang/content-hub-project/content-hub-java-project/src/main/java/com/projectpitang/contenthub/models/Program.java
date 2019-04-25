package com.projectpitang.contenthub.models;

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

    @Size(min = 150, max = 200)
    @Column(name = "prog_cl_title")
    private String title;

    @Column(name = "prog_cl_overview")
    private String overview;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Cast.class)
    @JoinColumn(name="cast_cl_id")
    private Cast cast;

    @Size(min = 30, max = 50)
    @Column(name = "prog_cl_prodcountry")
    private String productionCountry;

    @Size(min = 2, max = 20)
    @Column(name = "prog_cl_language")
    private String language;

    @Temporal(TemporalType.DATE)
    @Column(name = "prog_cl_releaseyear")
    private Date releaseYear;

    @Column(name = "prog_cl_runtime")
    private int runtime;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ProgramGenre.class, mappedBy = "genre")
    private Set<ProgramGenre> programGenres;


}
