package com.projectpitang.contenthub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_serie")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class Serie extends Program{

    @Column(name = "ser_cl_seansons")
    private int seansons;

}
