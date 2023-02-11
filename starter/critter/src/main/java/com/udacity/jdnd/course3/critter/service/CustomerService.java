package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetService petService;

    public Long saveCustomer(Customer customer){

        return customerRepository.save(customer).getId();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Boolean exists(Customer customer){
     return  (getAllCustomers().contains(customer));
    }

    public Customer getCustomerById(Long customerId){
/*        if (customerRepository.findById(customerId).isPresent())
            return customerRepository.findById(customerId).get();
        else
            throw new UnsupportedOperationException();*/
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer findOwnerByPet(Long petId){
        Pet pet= petService.findPetById(petId);
        return customerRepository.findOwnerByPets(pet);
    }
}
