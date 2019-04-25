package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_genre")
public class Genre implements IObjectPersistent<Long> {

    @Id
    @Column(name = "gen_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gen_cl_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ProgramGenre.class, mappedBy = "program")
    private Set<ProgramGenre> programGenres;

}
