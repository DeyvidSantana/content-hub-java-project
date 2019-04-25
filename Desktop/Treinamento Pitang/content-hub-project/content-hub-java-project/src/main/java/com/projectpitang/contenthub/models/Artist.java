package com.projectpitang.contenthub.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "AR")
public class Artist extends Person {
}
