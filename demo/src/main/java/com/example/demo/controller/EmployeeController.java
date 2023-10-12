package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.implementation.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeServiceImpl employeeService;
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee, employee.getBranch().getId());
    }
    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getAllEmployee(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
