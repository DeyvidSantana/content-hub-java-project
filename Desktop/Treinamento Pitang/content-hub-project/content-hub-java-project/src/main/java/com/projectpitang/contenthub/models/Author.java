package com.projectpitang.contenthub.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "AU")
public class Author extends Person {
}
