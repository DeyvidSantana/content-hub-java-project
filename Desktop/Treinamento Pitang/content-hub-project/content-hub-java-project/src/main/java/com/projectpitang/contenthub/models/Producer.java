package com.projectpitang.contenthub.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PR")
public class Producer extends Person {
}
