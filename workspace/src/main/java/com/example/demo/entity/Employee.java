package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "employees")
public class Employee implements Serializable {
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

    // 所属セクション
    @Column(nullable = false)
    private String section_id;

    // メールアドレス
    @Column(nullable = false)
    private String mail;

    // 性別
    @Column(nullable = false)
    private String gender_id;

}