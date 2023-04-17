package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     * @param employee
     * @throws Exception
     */
    public void create(Employee employee) throws Exception{

        try {
            employeeRepository.save(employee);

            //throw new Exception("エラーです！");
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
