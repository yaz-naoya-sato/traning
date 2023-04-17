/*
 【DB】
 論理名：社員名簿
 物理名：company_derectory
 【テーブル】
 論理名：社員
 物理名：employee
*/
CREATE TABLE company_directory.employee(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY, -- ID
	employee_id CHAR(10) NOT NULL UNIQUE,			-- 社員ID
	family_name VARCHAR(20) NOT NULL,				-- 社員名（姓）
	first_name VARCHAR(20) NOT NULL,				-- 社員名（名）
	section_id INTEGER NOT NULL,					-- 所属セクション
	mail VARCHAR(256) NOT NULL,						-- メールアドレス
	gender_id INTEGER NOT NULL					-- 性別
);