package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //No code!
    //What a magic!!

   // List<Employee> findAllBySkillsAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek DaysAvailable);

    List<Employee> findAllByDaysAvailableContaining(DayOfWeek day);
}
