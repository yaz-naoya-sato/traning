package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    // LOGGER 共通化実施予定 TODO
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean isInsertDuplication(String employeeId) {

        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElse(null);
        return employee == null;

    }

    /**
     * 社員情報登録サービスメソッド
     *
     * @param employee 社員情報
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class) // メソッド開始時にトランザクションを開始、終了時にコミットする
    public void create(Employee employee) throws IllegalArgumentException, DataAccessException {

        try {
            logger.debug("社員情報の登録を実施します。");

            // JpaRepositoryから継承したsaveメソッドを使用
            // employeeエンティティの中身をDBに登録
            employeeRepository.save(employee);

            logger.debug("社員情報の登録が完了しました。");

        } catch (DuplicateKeyException ex) {
            // データ整合性エラー
            logger.error("社員IDが重複しています。社員ID:{}\r\n" + ex, employee.getEmployeeId());
            throw ex;
        } catch (IllegalArgumentException ex) {
            // 不正、不適切な引数エラー
            logger.error("不正な引数が渡されました。引数リスト:{}\r\n" + ex, employee);
            throw ex;
        } catch (DataAccessException ex) {
            // データアクセス例外
            logger.error("データアクセス例外が発生しました。\r\n" + ex);
            throw ex;
        }

    }

    /**
     * 社員情報更新サービスメソッド
     *
     * @param employee 社員情報
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class) // メソッド開始時にトランザクションを開始、終了時にコミットする
    public void update(Employee employee) throws IllegalArgumentException, DataAccessException {

        try {
            logger.debug("社員情報の更新を実施します。");

            // JpaRepositoryから継承したsaveメソッドを使用
            // employeeエンティティの中身をDBに登録
            employeeRepository.save(employee);

            logger.debug("社員情報の更新が完了しました。");

        } catch (DuplicateKeyException ex) {
            // データ整合性エラー
            logger.error("社員IDが重複しています。社員ID:{}\r\n" + ex, employee.getEmployeeId());
            throw ex;
        } catch (IllegalArgumentException ex) {
            // 不正、不適切な引数エラー
            logger.error("不正な引数が渡されました。引数リスト:{}\r\n" + ex, employee);
            throw ex;
        } catch (DataAccessException ex) {
            // データアクセス例外
            logger.error("データアクセス例外が発生しました。\r\n" + ex);
            throw ex;
        }

    }

    @Transactional(rollbackFor = Exception.class) // メソッド開始時にトランザクションを開始、終了時にコミットする
    public void delete(Employee employee) throws IllegalArgumentException, DataAccessException {

        try {
            logger.debug("社員情報の削除を実施します。");

            // JpaRepositoryから継承したdeleteメソッドを使用
            // employeeエンティティの中身をDBから削除
            employeeRepository.delete(employee);

            logger.debug("社員情報の削除が完了しました。削除対象:{}", employee);

        } catch (IllegalArgumentException ex) {
            // 不正、不適切な引数エラー
            logger.error("不正な引数が渡されました。引数リスト:{}\r\n" + ex, employee);
            throw ex;
        } catch (OptimisticLockingFailureException ex) {
            // 対象データなしエラー
            logger.error("対象データが存在しません\r\n" + ex);
            throw ex;
        } catch (DataAccessException ex) {
            // データアクセス例外
            logger.error("データアクセス例外が発生しました。\r\n" + ex);
            throw ex;
        }

    }

    /**
     * IDで検索
     */
    public Employee findById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return new Employee();
        }
        return employee;
    }


    /**
     * 社員IDで検索し、重複を確認
     */
    public Optional<Employee> findByEmployeeId(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    /**
     * 社員情報一覧
     * 条件：社員IDの昇順
     *
     * @return 社員情報一覧
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "employeeId")
        );
    }
}
