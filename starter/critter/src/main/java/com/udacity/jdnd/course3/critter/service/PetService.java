package com.udacity.jdnd.course3.critter.service;

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

    public Long savePet(Pet pet){
        return petRepository.save(pet).getId();
    }

    public List<Pet> findPetsByOwner(long ownerId){
        return petRepository.findPetsByOwner(ownerId);
    }

    public Pet findPetById(long petId){
        Optional<Pet> pet = petRepository.findById(petId);
        if (pet.isPresent())
                return pet.get();
        else
            throw new UnsupportedOperationException();
    }
}
