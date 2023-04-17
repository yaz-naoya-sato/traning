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
    public String create(@ModelAttribute Employee employee, Model model) throws Exception {

        try {
            // サービスのcreateメソッド呼び出し
            employeeService.create(employee);

            // employeeに入力フォームの内容が格納されているため初期化
            model.addAttribute("employees", new Employee());

            // DBコミットが成功した場合は、成功メッセージを表示
            model.addAttribute("res","データを登録しました");
        } catch (Exception e) {
            model.addAttribute("res","データ登録に失敗しました");
        }

        return "employees/employee_result";
    }

    //社員一覧
    @GetMapping("/employee_list")
    public String getList(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        // 変数名employeesに値をセットしてThymeleafに渡す
        model.addAttribute("employees", employees);
        // src/main/resources/static/employees/employee_list.htmlを表示する
        return "employees/employee_list";
    }
}
