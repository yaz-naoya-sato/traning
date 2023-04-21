'use strict'
// 所属セクションリスト
var sectionList = [
    {val:"0", txt:"選択してください"},
    {val:"1", txt:"シス開"},
    {val:"2", txt:"グロカル"},
    {val:"3", txt:"ビジソル"}
];

// 性別リスト
var genderList = [
    {val:"1", txt:"男性"},
    {val:"2", txt:"女性"}
];

// 値を初期化
var sectionValue = "0";
var genderValue = "0";

// 所属セクションセレクトボックス、性別ラジオボタンの設定
function readFirst(){

    let selectDiv = document.getElementById("section_id");

    //所属セクションリストをループ処理で値を取り出してセレクトボックスにセットする
    for(let i=0;i<sectionList.length;i++){

      let opt = document.createElement("option");  // optionタグを作成する
      opt.value = sectionList[i].val;  //value値
      opt.text = sectionList[i].txt;   //テキスト値

      // responseから所属セクションの値を取得し、セレクトボックスを設定状態にする
      if(sectionList[i].val == employeeForm.section_id ){
          opt.selected = true;
      }

      document.getElementById("section_id").appendChild(opt);
    }

    // 値の保持
    var savedValue = sessionStorage.getItem("selectedSection");


    //性別リストをループ処理で値を取り出してラジオボタンにセットする
    let radioDiv = document.getElementById("gender_id");

    for(let i = 0; i < genderList.length; i++) {
      let radioBtn = document.createElement("input");
      radioBtn.type = "radio";
      radioBtn.name = "gender_id";
      radioBtn.value = genderList[i].val;

      // responseから性別の値を取得し、ラジオボタンを設定状態にする
      if(genderList[i].val == employeeForm.gender_id ){
          radioBtn.checked = true;
      }

      let label = document.createElement("label");
      label.appendChild(radioBtn);
      label.appendChild(document.createTextNode(genderList[i].txt));

      radioDiv.appendChild(label);
    }
};