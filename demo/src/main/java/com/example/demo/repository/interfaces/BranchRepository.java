package com.example.demo.repository.interfaces;

import com.example.demo.entity.Branch;

import java.util.Optional;


public interface BranchRepository {
    Branch saveBranch(Branch branch);
    Optional<Branch> getBranch(int id);

}
