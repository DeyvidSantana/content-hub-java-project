package com.projectpitang.contenthub.models;

import com.projectpitang.contenthub.infrastructure.IObjectPersistent;

import javax.persistence.*;

@Entity
@Table(name = "tb_program_genre")
public class ProgramGenre implements IObjectPersistent<ProgramGenrePK> {

    @EmbeddedId
    private ProgramGenrePK programGenrePK;

    @ManyToOne(optional = true)
    @MapsId("programId")
    private Program program;

    @ManyToOne
    @MapsId("genreId")
    private Genre genre;

}
