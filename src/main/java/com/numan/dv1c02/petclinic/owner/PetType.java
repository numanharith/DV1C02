package com.numan.dv1c02.petclinic.owner;

import com.numan.dv1c02.petclinic.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {
}
