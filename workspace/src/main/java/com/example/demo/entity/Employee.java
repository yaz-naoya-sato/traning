package com.example.demo.entity;

import com.example.demo.Duplication;
import jakarta.persistence.*;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 社員(employee)テーブルのエンティティ
 */
@Entity
@Data // getter/setterなどの自動実装
@Table(name = "employee")
public class Employee implements Serializable {
    public interface Group1 {}
    public interface Group2 {}
    public interface Group3 {}
    public interface Group4 {}
    public interface Group5 {}


    @GroupSequence({Group1.class,Group2.class,Group3.class,Group4.class,Group5.class})
    public interface All {}


    // ID
    @Id // エンティティの主キーを設定
    @GeneratedValue(strategy = GenerationType.IDENTITY) // エンティティの主キーを設定
    private int id;

    // 社員ID
    @Column(name = "employee_id")
    @NotEmpty(message = "社員IDを入力してください", groups = Group1.class)
    @Length(min = 10, max = 10, message = "社員IDは10文字で入力してください", groups = Group2.class)
    @Duplication(groups = Group4.class)
    @Pattern(regexp="^YZ\\d{8}$", message = "社員IDを正しく入力してください", groups = Group5.class)
    private String employeeId;

    // 社員名(姓)
    @Column(name = "family_name")
    @NotEmpty(message = "社員名(姓)を入力してください", groups = Group1.class)
    @Length(min = 0, max = 20,message = "社員名(姓)は20文字以下で入力してください", groups = Group3.class)
    private String familyName;

    // 社員名(名)
    @Column(name = "first_name")
    @NotEmpty(message = "社員名(名)を入力してください", groups = Group1.class)
    @Length(min = 0, max = 20, message = "社員名(名)は20文字以下で入力してください", groups = Group3.class)
    private String firstName;

    // 所属セクション
    //@Column(name = "section_id")
    @NotNull(message = "所属セクションを選択してください", groups = Group1.class)
    @Range(min = 1, max = 3, message = "所属セクションを選択してください", groups = Group1.class)
    private Integer section_id;

    // メールアドレス
    @NotEmpty(message = "メールアドレスを入力してください", groups = Group1.class)
    @Length(min = 0, max = 256,message = "メールアドレスは256文字以下で入力してください", groups = Group3.class)
    @Pattern(regexp="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+$", message = "メールアドレスを正しく入力してください", groups = Group5.class)
    private String mail;

    // 性別
    //@Column(name = "gender_id")
    @NotNull(message = "性別を選択してください", groups = Group1.class)
    @Range(min = 1, max = 2, message = "性別を選択してください", groups = Group1.class)
    private Integer gender_id;

}