package com.example.demo.repository.interfaces;

import com.example.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(int id);
    Employee update(Employee employee);
    void delete(int id);
}
