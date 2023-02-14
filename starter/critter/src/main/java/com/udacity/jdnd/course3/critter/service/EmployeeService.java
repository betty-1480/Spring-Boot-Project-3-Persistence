package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public long saveEmployee(Employee employee){

        return employeeRepository.save(employee).getId();
    }

    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
/*        if (employeeRepository.findById(id).isPresent())
           return employeeRepository.findById(id).get();
        else
            throw new UnsupportedOperationException();*/
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable,Employee employee){
        assert employee != null;
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek){
        //return employeeRepository.findAllBySkillsAndDaysAvailable(skills,dayOfWeek);
        return employeeRepository.findAllByDaysAvailableContaining(dayOfWeek);
    }

}
