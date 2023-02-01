package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long id= petService.savePet(convertPetDTOToEntity(petDTO));
        petDTO.setId(id);
        System.out.println(petDTO.getOwnerId());
        return petDTO;
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findPetById(petId);
        return convertPetEntityToDTO(pet);
        //throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwner(ownerId);
        return pets.stream().map(this::convertPetEntityToDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    public Pet convertPetDTOToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        return pet;
    }

    public PetDTO convertPetEntityToDTO(Pet pet){
        PetDTO petDTO=new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        return petDTO;
    }

}
