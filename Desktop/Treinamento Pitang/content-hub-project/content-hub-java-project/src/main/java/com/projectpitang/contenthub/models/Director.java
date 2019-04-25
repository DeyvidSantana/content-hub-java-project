package com.projectpitang.contenthub.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DI")
public class Director extends Person {
}
