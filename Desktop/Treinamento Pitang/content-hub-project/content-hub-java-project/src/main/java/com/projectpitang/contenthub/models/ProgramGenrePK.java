package com.projectpitang.contenthub.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProgramGenrePK implements Serializable {

    @Column(name = "program_id")
    private Long programId;

    @Column(name = "genre_id")
    private Long genreId;

}
