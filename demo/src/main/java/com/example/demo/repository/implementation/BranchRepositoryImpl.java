package com.example.demo.repository.implementation;

import com.example.demo.entity.Branch;
import com.example.demo.entity.Employee;
import com.example.demo.repository.interfaces.BranchRepository;
import com.example.demo.repository.interfaces.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BranchRepositoryImpl implements BranchRepository {
    private SessionFactory factory;
    public BranchRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public Branch saveBranch(Branch branch) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(branch);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return branch;
    }
    @Override
    public Optional<Branch> getBranch(int id){
        Session session = factory.getCurrentSession();
        Branch branch = null;
        try {
            session.beginTransaction();
            branch = session.get(Branch.class, id);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return Optional.ofNullable(branch);
    }
}
