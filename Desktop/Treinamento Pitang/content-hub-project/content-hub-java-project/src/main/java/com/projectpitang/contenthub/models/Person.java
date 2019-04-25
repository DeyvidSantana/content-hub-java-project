package com.projectpitang.contenthub.models;

import com.example.mapone.utils.PersonGenre;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "per_cl_type", length = 2, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public class Person implements IObjectPersistent<Long> {

    @Id
    @Column(name = "per_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "per_cl_name")
    private String name;

    @Column(name = "per_cl_height")
    private Double height;

    @Column(name = "per_cl_citybirth")
    private String cityBirth;

    @Column(name = "per_cl_countrybirth")
    private String countryBirth;

    @Column(name = "per_cl_genre")
    private PersonGenre genre;

    @Column(name = "per_cl_type", insertable=false, updatable=false)
    private String type;

}
