package com.numan.dv1c02.petclinic.owner;

import com.numan.dv1c02.petclinic.model.NamedEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    @OrderBy("visit_date ASC")
    private Set<Visit> visits = new LinkedHashSet<>();

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public PetType getType() {
        return type;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void addVisit(Visit visit) {
        getVisits().add(visit);
    }
}
