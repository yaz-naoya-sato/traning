package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Optional<Employee> findByEmployeeId(String employee_id);

    /**
     * ソート条件付きfindAll
     * employeeIdの昇順
     * @return
     */
    public List<Employee> findAll();
}
