package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //No code!
    //What a magic!!

    //findOwnerByPet or findOwnerByPetId won't work!!
    // because there is no such property for Customer!!
    Customer findOwnerByPets(Pet pet); //Spring-Data-JPA Tool: method names automatically create JPQL!!
}
