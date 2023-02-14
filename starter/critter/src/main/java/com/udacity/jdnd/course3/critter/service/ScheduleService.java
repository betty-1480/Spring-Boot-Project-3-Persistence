package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional // will declare all methods of the class transactional
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
