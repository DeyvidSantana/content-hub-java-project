package com.projectpitang.contenthub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_cast")
@JsonIgnoreProperties({"program","cast"})
public class Cast implements IObjectPersistent<Long> {

    @Id
    @Column(name = "cast_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cast_cl_idcreditapi")
    private Long idCreditApi;

    @Size(min = 1, max = 200)
    @Column(name = "cast_cl_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="cast")
    private Program program;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Person.class)
    @JoinTable(name = "tb_cast_person",
                joinColumns = @JoinColumn(referencedColumnName = "cast_cl_id"),
                inverseJoinColumns = {@JoinColumn(referencedColumnName = "per_cl_id")})
    /*@JsonManagedReference
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "cast")*/
    private List<Person> cast;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cast cast = (Cast) o;
        return Objects.equal(id, cast.id);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCreditApi() {
        return idCreditApi;
    }

    public void setIdCreditApi(Long idCreditApi) {
        this.idCreditApi = idCreditApi;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }
}
