package com.example.demo.service.implementation;

import com.example.demo.entity.Branch;
import com.example.demo.repository.implementation.BranchRepositoryImpl;
import com.example.demo.service.interfaces.BranchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    BranchRepositoryImpl branchRepository;
    public BranchServiceImpl(BranchRepositoryImpl branchRepository) {
        this.branchRepository = branchRepository;
    }
    @Override
    public Branch addBranch(Branch branch) {
        return branchRepository.saveBranch(branch);
    }
}
