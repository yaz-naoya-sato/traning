package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    // 社員ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String employee_id;

    // 社員名(姓)
    @Column(nullable = false)
    private String family_name;

    // 社員名(名)
    @Column(nullable = false)
    private String first_name;
}