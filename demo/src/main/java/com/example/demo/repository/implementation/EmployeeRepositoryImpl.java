package com.example.demo.repository.implementation;

import com.example.demo.entity.Employee;
import com.example.demo.repository.interfaces.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private SessionFactory factory;
    public EmployeeRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Employee save(Employee employee) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Session session = factory.getCurrentSession();
        List<Employee> employees = null;
        try {
            session.beginTransaction();
            employees = session.createQuery("select e from Employee e", Employee.class).list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Optional<Employee> findById(int id) {
        Session session = factory.getCurrentSession();
        Employee employee = null;
        try {
            session.beginTransaction();
            employee = session.get(Employee.class, id);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Employee update(Employee employee) {
        Session session = factory.getCurrentSession();
        Employee actualEmployee = null;
        try {
            session.beginTransaction();
            actualEmployee = session.get(Employee.class, employee.getId());
            if (actualEmployee != null){
                actualEmployee.setName(employee.getName());
                actualEmployee.setNationalId(employee.getNationalId());
                actualEmployee.setAge(employee.getAge());
                actualEmployee.setBranch(employee.getBranch());
                session.update(actualEmployee);
            }
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return actualEmployee;
    }

    @Override
    public void delete(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null){
                session.delete(employee);
            }
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
}
