package com.projectpitang.contenthub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;
import com.projectpitang.contenthub.utils.PersonGender;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@DiscriminatorValue("PE")
@Table(name = "tb_person")
@JsonIgnoreProperties({"cast"})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "per_cl_type", length = 2, discriminatorType = DiscriminatorType.STRING)
public class Person implements IObjectPersistent<Long> {

    @Id
    @Column(name = "per_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "per_cl_idapi")
    private Long idApi;

    @Size(min = 1, max = 200)
    @Column(name = "per_cl_name")
    private String name;

    @Column(name = "per_cl_height")
    private Double height;

    @Size(min = 1, max = 100)
    @Column(name = "per_cl_citybirth")
    private String cityBirth;

    @Size(min = 1, max = 100)
    @Column(name = "per_cl_countrybirth")
    private String countryBirth;

    @Column(name = "per_cl_genre")
    private PersonGender gender;

    @ManyToMany(mappedBy = "cast")
    private Set<Cast> cast;

    @Column(name = "per_cl_type", insertable=false, updatable=false)
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(id, person.id);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getCityBirth() {
        return cityBirth;
    }

    public void setCityBirth(String cityBirth) {
        this.cityBirth = cityBirth;
    }

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public Set<Cast> getCast() {
        return cast;
    }

    public void setCast(Set<Cast> cast) {
        this.cast = cast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
