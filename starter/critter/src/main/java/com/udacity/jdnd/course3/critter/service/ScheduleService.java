package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

     public Schedule createSchedule (Schedule schedule){
         return scheduleRepository.save(schedule);
     }

     public List<Schedule> getAllSchedules(){
         return scheduleRepository.findAll();
     }

    public List<Schedule> getScheduleForPet(long petId){
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){

         return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId){
         return scheduleRepository.findAllByPetsOwnerId(customerId);
    }
}
