package com.projectpitang.contenthub.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tb_cast")
public class Cast {

    @Id
    @Column(name = "cast_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 200)
    @Column(name = "cast_cl_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="cast")
    private Program program;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Person.class)
    @JoinTable(name = "tb_cast_person",
                joinColumns = @JoinColumn(referencedColumnName = "cast_cl_id"),
                inverseJoinColumns = {@JoinColumn(referencedColumnName = "per_cl_id")})
    private Set<Person> cast;

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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Set<Person> getCast() {
        return cast;
    }

    public void setCast(Set<Person> cast) {
        this.cast = cast;
    }
}
