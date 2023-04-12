package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getList(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        // 変数名employeesに値をセットしてThymeleafに渡す
        model.addAttribute("employees", employees);
        // src/main/resources/static/employees/employee_list.htmlを表示する
        return "employees/employee_list";
    }
}
