package com.example.demo.validate;

import com.example.demo.Duplication;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicationValidator implements ConstraintValidator<Duplication, String> {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Employee employeeId = employeeService.findByEmployeeId(value).orElse(null);
        if(employeeId == null){
            return true;
        }
        return false;
    }
}
