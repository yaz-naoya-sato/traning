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
@Table(name = "employee")
public class Employee implements Serializable {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 社員ID
    @Column(length=10, nullable = false)
    private String employee_id;

    // 社員名(姓)
    @Column(length=20, nullable = false)
    private String family_name;

    // 社員名(名)
    @Column(length=20, nullable = false)
    private String first_name;

    // 所属セクション
    @Column(nullable = false)
    private int section_id;

    // メールアドレス
    @Column(length=256, nullable = false)
    private String mail;

    // 性別
    @Column(nullable = false)
    private int gender_id;

}