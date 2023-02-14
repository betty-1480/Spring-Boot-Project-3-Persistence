package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer=convertCustomerDTOToEntity(customerDTO);
        Long customerId= customerService.saveCustomer(customer);
        customerDTO.setId(customerId);
        return customerDTO;
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers=customerService.getAllCustomers();
        return customers.stream().map(this::convertCustomerEntityToDTO).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerEntityToDTO(customerService.findOwnerByPet(petId));
        //throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
       Employee employee=convertEmployeeDTOToEntity(employeeDTO);
       long id= employeeService.saveEmployee(employee);
       employeeDTO.setId(id);
       return employeeDTO;
        //throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return convertEmployeeEntityToDTO(employee);
        //throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee=employeeService.getEmployeeById(employeeId);
        employeeService.setAvailability(daysAvailable, employee);
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        List<Employee> employees = employeeService.findEmployeesForService(skills,dayOfWeek);
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee e : employees){
            if(e.getSkills().containsAll(skills)) {
                availableEmployees.add(e);
            }
        }
        return availableEmployees.stream().map(this::convertEmployeeEntityToDTO).collect(Collectors.toList());
    }


    public  CustomerDTO convertCustomerEntityToDTO(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return customerDTO;
    }

    public  Customer convertCustomerDTOToEntity(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }

    public EmployeeDTO convertEmployeeEntityToDTO(Employee employee){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
}
