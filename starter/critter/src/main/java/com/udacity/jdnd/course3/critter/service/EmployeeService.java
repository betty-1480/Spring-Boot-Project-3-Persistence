package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public long saveEmployee(Employee employee){
        return employeeRepository.save(employee).getId();
    }

    public Employee getEmployeeById(long id){
        if (employeeRepository.findById(id).isPresent())
           return employeeRepository.findById(id).get();
        else
            throw new UnsupportedOperationException();
    }
}
