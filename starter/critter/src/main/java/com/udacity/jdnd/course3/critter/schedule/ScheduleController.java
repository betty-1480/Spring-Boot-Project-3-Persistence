package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule= scheduleService.createSchedule(convertScheduleDTOToEntity(scheduleDTO));
        return convertScheduleEntityToDTO(schedule);
        //throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules=scheduleService.getAllSchedules();
        return schedules.stream().map(this::convertScheduleEntityToDTO).collect(Collectors.toList());
       // throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules= scheduleService.getScheduleForPet(petId);
        return schedules.stream().map(this::convertScheduleEntityToDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
        List<Schedule> schedules=scheduleService.getScheduleForEmployee(employeeId);
        return schedules.stream().map(this::convertScheduleEntityToDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules=scheduleService.getScheduleForCustomer(customerId);
        return schedules.stream().map(this::convertScheduleEntityToDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule=new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);

        //Explicit mapping between petId and Pet is required
        List<Pet> pets = scheduleDTO.getPetIds().stream().map(petService::findPetById).collect(Collectors.toList());
        schedule.setPets(pets);

        //Explicit mapping between employeeId and Employee are required
        List<Employee> employees= scheduleDTO.getEmployeeIds().stream().map(employeeService::getEmployeeById).collect(Collectors.toList());
        schedule.setEmployees(employees);

        return schedule;
    }

    ScheduleDTO convertScheduleEntityToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO=new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);

        //Explicit mapping ie required
        List<Long> petIds=schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        scheduleDTO.setPetIds(petIds);

        //Explicit mapping is required
        List<Long> employeeIds=schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(employeeIds);

        return scheduleDTO;
    }

}
