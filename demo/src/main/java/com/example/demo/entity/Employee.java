package com.example.demo.entity;

import com.example.demo.validation.ArabicName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotBlank(message = "Name is required")
    @ArabicName(message = "Invalid Arabic name")
    private String name;
    @Size(max = 14, min = 14, message = "National ID must be equal 14 digit number")
    @Column(name = "national_id", unique = true)
    private String nationalId;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Employee() {
    }

    public Employee(int id, String name, String nationalId, int age, Branch branch) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.age = age;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
