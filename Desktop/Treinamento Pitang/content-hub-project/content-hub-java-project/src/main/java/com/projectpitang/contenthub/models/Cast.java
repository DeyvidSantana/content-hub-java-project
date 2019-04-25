package com.projectpitang.contenthub.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_cast")
public class Cast {

    @Id
    @Column(name = "cast_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy="cast")
    private Program program;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Person.class)
    @JoinTable(name = "tb_cast_person",
                joinColumns = @JoinColumn(referencedColumnName = "cast_cl_id"),
                inverseJoinColumns = {@JoinColumn(referencedColumnName = "per_cl_id")})
    private Set<Person> cast;
}
