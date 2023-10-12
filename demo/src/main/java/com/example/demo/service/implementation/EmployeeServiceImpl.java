package com.example.demo.service.implementation;

import com.example.demo.entity.Branch;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.implementation.BranchRepositoryImpl;
import com.example.demo.repository.implementation.EmployeeRepositoryImpl;
import com.example.demo.service.interfaces.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepositoryImpl employeeRepository;
    BranchRepositoryImpl branchRepository;
    public EmployeeServiceImpl(EmployeeRepositoryImpl employeeRepository, BranchRepositoryImpl branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }
    @Override
    public Employee addEmployee(Employee employee, int id) {
        int age = validateAgeFromNationalId(employee.getNationalId());
        if (age < 21) {
            throw new IllegalArgumentException("Employee must be at least 18 years old");
        }
        Optional<Branch> branch = branchRepository.getBranch(id);
        employee.setBranch(branch.get());
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new EmployeeNotFoundException("Employee is not found"));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> actualEmployee = employeeRepository.findById(employee.getId());
        if(actualEmployee.isPresent()) {
            employeeRepository.update(employee);
        }
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            employeeRepository.delete(id);
        } else {
            throw new EmployeeNotFoundException("Employee is not found with id: "+id);
        }
    }
    private int validateAgeFromNationalId(String nationalId) {
        String dateOfBirthStr = nationalId.substring(0, 8);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
            Date currentDate = new Date(); // Current date
            // Calculate the age
            int age = currentDate.getYear() - dateOfBirth.getYear();
            if (currentDate.getMonth() < dateOfBirth.getMonth() ||
                    (currentDate.getMonth() == dateOfBirth.getMonth() && currentDate.getDate() < dateOfBirth.getDate())) {
                age--;
            }
            return age;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
