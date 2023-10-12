package com.example.demo.controller;

import com.example.demo.entity.Branch;
import com.example.demo.entity.Employee;
import com.example.demo.service.implementation.BranchServiceImpl;
import com.example.demo.service.implementation.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private BranchServiceImpl branchService;
    public BranchController(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }
}
