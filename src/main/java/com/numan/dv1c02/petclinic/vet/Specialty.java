package com.numan.dv1c02.petclinic.vet;

import com.numan.dv1c02.petclinic.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {
}