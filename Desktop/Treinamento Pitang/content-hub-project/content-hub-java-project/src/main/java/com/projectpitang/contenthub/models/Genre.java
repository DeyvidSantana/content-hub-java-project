package com.projectpitang.contenthub.models;

import com.google.common.base.Objects;
import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tb_genre")
public class Genre implements IObjectPersistent<Long> {

    @Id
    @Column(name = "gen_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 50)
    @Column(name = "gen_cl_name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "genres")
    private Set<Program> programs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equal(id, genre.id);
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

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }
}
