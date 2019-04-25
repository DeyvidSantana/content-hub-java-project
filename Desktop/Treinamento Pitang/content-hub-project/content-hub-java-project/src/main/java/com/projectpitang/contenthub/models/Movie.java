package com.projectpitang.contenthub.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movie")
@PrimaryKeyJoinColumn(name = "prog_cl_id")
public class Movie extends Program{
}
