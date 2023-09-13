import React from "react";
import WriteYourName from "@/assets/img/WriteYourName.png";
import Kakao from "@/assets/img/Kakao.png";
import Google from "@/assets/img/Google.png";
import Naver from "@/assets/img/Naver.png";
import MenuButton from "@/assets/img/MenuButton.png";
import styles from "./styles.module.css";

const login = () => {
  return (
    <div className={styles.container}>
      <div className={styles.title}>
        <img src={WriteYourName} />
        <img className={styles.menuButton} src={MenuButton} />
      </div>
      <div className={styles.img}>
        <div className={styles.id}>
          <p className={styles.idFont}>계정이름</p>
          <input className={styles.inputId} />
        </div>
        <div className={styles.pw}>
          <p className={styles.pwFont}>비밀번호</p>
          <input className={styles.inputPW} />
          <div className={styles.signUp}>
            <button onClick={() => {}}>회원가입</button>
          </div>
          <button className={styles.loginButton}>Login</button>
          <img
            className={styles.Kakao}
            src={Kakao}
            onClick={() => {
              console.log(1);
            }}
          />
          <img
            className={styles.Google}
            src={Google}
            onClick={() => {
              console.log(2);
            }}
          />
          <img
            className={styles.Naver}
            src={Naver}
            onClick={() => {
              console.log(3);
            }}
          />
        </div>
      </div>
    </div>
  );
};

export default login;
