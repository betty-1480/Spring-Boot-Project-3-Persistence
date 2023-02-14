package com.udacity.jdnd.course3.critter.entity;


import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="PET")
public class Pet {
    @Id
    @GeneratedValue
    @Column(name = "PET_ID")
    private long id;

    @Column(name="PET_TYPE")
    private PetType type;

    @Column(name="PET_NAME")
    @Nationalized
    private String name;

    //Bidirectional, many pets belong to one owner
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID") // many side of the relationship should have a physical column
    private Customer owner=new Customer();

    @Column(name="PET_BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name="NOTES")
    private String notes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;

    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
