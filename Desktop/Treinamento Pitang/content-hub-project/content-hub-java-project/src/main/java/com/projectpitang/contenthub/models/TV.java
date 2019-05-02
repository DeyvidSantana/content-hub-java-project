package com.projectpitang.contenthub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tv")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class TV extends Program{

    @Column(name = "tv_cl_seansons")
    private int seansons;

    public int getSeansons() {
        return seansons;
    }

    public void setSeansons(int seansons) {
        this.seansons = seansons;
    }
}
