package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_ID")
    private Long id;

    @Nationalized //@Type(type="nstring")
    @Column(name="CUSTOMER_NAME", nullable = false)
    private String name;

    @Column(name="PHONE_NUMBER", length = 256, nullable = false)
    private String phoneNumber;

    @Column(name="NOTES",length = 256)
    private String notes;

    //Bidirectional, one owner can have multiple pets.
    @OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
    private List<Pet> pets =new ArrayList<>();

    // @Entity class should have a public/ protected non-argument constructor
    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
