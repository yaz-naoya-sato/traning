function validateForm() {
  // 入力フォームのvalueを取得
  var employee_id = document.forms["myForm"]["employee_id"].value;
  var family_name = document.forms["myForm"]["family_name"].value;
  var first_name = document.forms["myForm"]["first_name"].value;
  var section_id = document.forms["myForm"]["section_id"].value;
  var mail = document.forms["myForm"]["mail"].value;
  var gender_id = document.forms["myForm"]["gender_id"].value;

  // 社員IDの桁数
  var id_count = 10
  // 社員名（姓、名）の最大桁数
  var name_count = 20
  // メールアドレスの最大桁数
  var mail_count = 256
  // 社員IDの書式パターン
  var id_pattern = /^YZ\d{8}$/;
  // メールアドレスの書式パターン
  var mail_pattern = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+$/;

  // バリデーションチェック
  // 社員ID
  // 必須
  if (employee_id == "") {
    alert("社員IDを入力してください。");
    return false;
  }

  // 桁数
  if (employee_id.length !== id_count) {
    alert(`社員IDは${id_count}文字で入力してください。`);
    return false;
  }

  // 書式
  if (!id_pattern.test(employee_id)) {
    alert("社員IDを正しく入力してください。");
    return false;
  }

  // 社員名（姓）
  // 必須
  if (family_name == "") {
    alert("社員名（姓）を入力してください。");
    return false;
  }

  // 桁数
  if (family_name.length > name_count) {
    alert(`社員名（姓）は${name_count}文字以下で入力してください。`);
    return false;
  }

  // 社員名（名）
  // 必須
  if (first_name == "") {
    alert("社員名（名）を入力してください。");
    return false;
  }

  // 桁数
  if (first_name.length > name_count) {
    alert(`社員名（名）は${name_count}文字以下で入力してください。`);
    return false;
  }

  // 所属セクション
  // 必須
  if (section_id == "") {
    alert("所属セクションを選択してください。");
    return false;
  }

  // メールアドレス
  // 必須
  if (mail == "") {
      alert("メールアドレスを入力してください。");
      return false;
  }

  // 桁数
  if (mail.length > mail_count) {
    alert(`メールアドレスは${mail_count}文字以下で入力してください。`);
    return false;
  }

  // 書式
  if (!mail_pattern.test(mail)) {
    alert("メールアドレスを正しく入力してください。");
    return false;
  }

  // 性別
  // 必須
  if (gender_id == "") {
    alert("性別を選択してください。");
    return false;
  }
}