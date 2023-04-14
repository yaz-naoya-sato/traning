package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // 社員登録画面()
    @GetMapping("/employee_reg")
    public String displayReg(Model model) {
        // 入力フォームで取り扱うオブジェクトを設定
        model.addAttribute("employees", new Employee());
        return "employees/employee_reg";
    }

    @PostMapping("/employee_reg")
    public String submitReg(@ModelAttribute Employee employee, Model model) {
        // employeeに入力フォームの内容が格納されているため初期化
        model.addAttribute("employees", new Employee());

        return "employees/employee_reg";
    }

    //社員一覧
    @GetMapping("/employees/employee_list")
    public String getList(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        // 変数名employeesに値をセットしてThymeleafに渡す
        model.addAttribute("employees", employees);
        // src/main/resources/static/employees/employee_list.htmlを表示する
        return "employees/employee_list";
    }
}
