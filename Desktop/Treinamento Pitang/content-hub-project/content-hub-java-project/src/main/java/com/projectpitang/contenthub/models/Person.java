package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;
import com.projectpitang.contenthub.utils.PersonGender;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tb_person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "per_cl_type", length = 2, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PE")
public class Person implements IObjectPersistent<Long> {

    @Id
    @Column(name = "per_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 150, max = 200)
    @Column(name = "per_cl_name")
    private String name;

    @Column(name = "per_cl_height")
    private Double height;

    @Size(min = 100, max = 150)
    @Column(name = "per_cl_citybirth")
    private String cityBirth;

    @Size(min = 100, max = 150)
    @Column(name = "per_cl_countrybirth")
    private String countryBirth;

    @Column(name = "per_cl_genre")
    private PersonGender gender;

    @ManyToMany(mappedBy = "cast")
    private Set<Cast> cast;

    @Column(name = "per_cl_type", insertable=false, updatable=false)
    private String type;

}
