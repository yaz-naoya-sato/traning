package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    // LOGGER 共通化実施予定 TODO
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    /**
     * EmployeeServiceのインスタンス化
     */
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Validator validator;

    /**
     * 社員情報登録画面の呼び出し
     * @return 社員情報登録画面
     */
    @GetMapping("/employee_reg")
    public String initReg(Employee employee, Model model) {
        logger.debug("社員情報登録画面の呼び出しを実施します。");
        model.addAttribute(employee);
        // 社員情報登録画面の返却
        return "employees/employee_reg";
    }

    /**
     * Postされた社員情報のDB登録
     * @param employee 社員(employee)テーブルのエンティティ
     * @param bindingResult バリデーション結果を表すI/F
     * @param model モデル属性を定義
     * @return 社員情報登録_結果画面
     */
    @PostMapping(value="/employee_reg", params="employeeReg")
    public String employeeReg(@ModelAttribute @Validated(Employee.All.class) @Valid Employee employee,
                              BindingResult bindingResult,
                              Model model) {

        try {
            logger.debug("POSTされた社員情報のバリデーションを実施します。");
            // 入力チェック判定

            if (bindingResult.hasErrors()){
                logger.warn("入力チェックエラーが発生しました。");

                model.addAttribute(employee);
                return "employees/employee_reg";
            }

            logger.debug("社員情報登録メソッド(service)の呼び出しを実施します。");
            // サービスのcreateメソッド呼び出し
            employeeService.create(employee);

            // employeeに入力フォームの内容が格納されているため初期化
            model.addAttribute("employee", new Employee());

            // DBコミットが成功した場合は、成功メッセージを表示
            model.addAttribute("res", "データを登録しました");

            // catchが冗長のため共通化対応 TODO
        } catch (CannotCreateTransactionException ex) {
            // トランザクションを作成できない場合は、失敗メッセージを表示
            logger.error("トランザクションの作成に失敗しました。\r\n" + ex);
            model.addAttribute("res","データ登録に失敗しました");
        } catch (IllegalArgumentException ex) {
            // 不正、不適切な引数エラー
            logger.error("不正な引数が渡されました。\r\n" + ex);
            model.addAttribute("res","データ登録に失敗しました");
        } catch (DataAccessException ex) {
            // データアクセス例外
            logger.error("データアクセス例外が発生しました。\r\n" + ex);
            model.addAttribute("res","データ登録に失敗しました");
        } catch (Exception ex) {
            // DBコミットが失敗した場合は、失敗メッセージを表示
            logger.error("予期しないエラーが発生しました。\r\n" + ex);
            model.addAttribute("res","データ登録に失敗しました");
        }
        logger.debug("社員情報登録_結果画面への返却を実施します。");
        // 社員情報登録_結果画面の返却
        return "employees/employee_result";
    }

    /**
     * 社員情報一覧画面の呼び出し＆会員一覧表示
     * @param model
     * @return
     */
    //社員一覧
    @GetMapping("/employee_list")
    public String getList(Model model) {
        List<Employee> employee = employeeService.getEmployees();
        Map<String, String> sections = new LinkedHashMap<String, String>();

        // 変数名employeesに値をセットしてThymeleafに渡す
        model.addAttribute("employee", employee);

        // src/main/resources/static/employees/employee_list.htmlを表示する
        return "employees/employee_list";
    }

}
