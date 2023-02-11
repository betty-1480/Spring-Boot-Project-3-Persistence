package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    public Pet savePet(Pet pet){ //Many side of the relationship
        if (customerService.getCustomerById(pet.getOwner().getId())==null) {
            throw new UnsupportedOperationException(); // if a Customer with Pet's ownerId does not exist in the DB
        }

        if (pet.getId()<=0)
            pet.setId(null);
        Long ownerId = pet.getOwner().getId(); //ownerId is coming from the PetDTO
        Customer owner=customerService.getCustomerById(ownerId); // get Customer with same Id as PetDTO's ownerId from database
        pet.setOwner(owner); //Pet class needs an owner not ownerId. Yet Pet table needs ownerId
        owner.getPets().add(pet); //this Pet is added to the Owner's list of Pet as well. Will this be in the DB?
        return petRepository.save(pet);
    }

    public List<Pet> findPetsByOwner(long ownerId){
        return petRepository.findPetsByOwnerId(ownerId);
    }

    public Pet findPetById(long petId){
        return petRepository.findById(petId).orElse(null);

/*        Optional<Pet> pet = petRepository.findById(petId);   Didn't work!!
        if (pet.isPresent())
                return pet.get();
        else
            throw new UnsupportedOperationException();*/
    }
}
