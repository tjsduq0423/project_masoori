import React, { useState, useEffect } from "react";
import Kakao from "@/assets/img/Kakao.png";
import Google from "@/assets/img/Google.png";
import Naver from "@/assets/img/Naver.png";
import BackCards from "@/assets/img/Login.png";
import styled from "styled-components";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import { userInfoState } from "@/states/userState";

const Container = styled.div`
  position: absolute;
  left: 15%;
  top: 15%;
  height: 70%;
  width: 70%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* background-color: rgba(0, 0, 0, 0.5); */
`;

const Img = styled.div`
  height: 75vh;
  width: 70vw;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  background-image: url(${BackCards});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

const Id = styled.div`
  position: relative;
  z-index: 1;
  top: 15%;
`;

const PW = styled.div`
  position: relative;
  top: 17%;
  z-index: 1;
`;

const PWCheck = styled.div`
  position: relative;
  top: 7%;
  z-index: 1;
`;
const FormLabel = styled.p`
  font-size: x-small;
  display: flex;
  margin-bottom: 2px;
`;

const Input = styled.input`
  width: 17vw;
  border-radius: 5px;
  border-width: 2px;
  border-color: black;
  height: 30px;
`;

const SignUp = styled.div`
  margin-top: 5px;
  margin-bottom: 5px;
  margin-right: 5px;
  display: flex;
  justify-content: end;
  font-size: x-small;
`;

const LoginButton = styled.button`
  width: 17vw;
  border-radius: 5px;
  border-color: black;
  border-width: 2px;
  margin-bottom: 25px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: smaller;
`;

const SocialLogin = styled.img`
  width: 17vw;
  margin-top: 5px;
`;

const Email = styled.div`
  position: relative;
  z-index: 1;
  top: 15%;
`;

const EmailCheck = styled.div`
  position: relative;
  top: 17%;
  z-index: 1;
`;

const NextButton = styled.button`
  width: 17vw;
  border-radius: 5px;
  border-color: black;
  border-width: 2px;
  margin-top: 20px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: smaller;
`;

const FinishButton = styled.button`
  width: 17vw;
  border-radius: 5px;
  border-color: black;
  border-width: 2px;
  margin-top: 30px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: smaller;
`;

const Login: React.FC = () => {
  const [modalState, setModalState] = useState<string>("로그인");

  const OAUTH2_REDIERECT_URI = `${process.env.REACT_APP_BASE_URL}/oauth/redirect`; /* 이거에대한페이지 생성 */
  const onSocialButtonClick = (socialName: string) => {
    const AUTH_URL = `${process.env.REACT_APP_SERVER_URL}/login/oauth2/authorization/${socialName}?redirect_uri=${OAUTH2_REDIERECT_URI}`;
    window.location.href = AUTH_URL;
  };

  const userInfo = useRecoilValue(userInfoState);
  const navigate = useNavigate();
  // 로그인 되어있는 유저라면 main 페이지로
  useEffect(() => {
    if (userInfo && userInfo.userId && userInfo.userId > 0) {
      navigate("/main");
    }
  }, [userInfo, navigate]);

  if (modalState === "로그인") {
    return (
      <Container>
        <Img>
          <Id>
            <FormLabel>계정이름</FormLabel>
            <Input />
          </Id>
          <PW>
            <FormLabel>비밀번호</FormLabel>
            <Input />
            <SignUp>
              <button
                onClick={() => {
                  setModalState("회원가입1");
                }}
              >
                회원가입
              </button>
            </SignUp>
            <LoginButton>Login</LoginButton>
            <SocialLogin
              src={Kakao}
              onClick={() => {
                onSocialButtonClick("kakao");
              }}
            />
            <SocialLogin
              src={Google}
              onClick={() => {
                onSocialButtonClick("google");
              }}
            />
            <SocialLogin
              src={Naver}
              onClick={() => {
                onSocialButtonClick("naver");
              }}
            />
          </PW>
        </Img>
      </Container>
    );
  } else if (modalState === "회원가입1") {
    return (
      <Container>
        <Img>
          <Email>
            <FormLabel>이메일</FormLabel>
            <Input />
          </Email>
          <EmailCheck>
            <FormLabel>이메일 확인</FormLabel>
            <Input />
            <SignUp>
              <NextButton
                onClick={() => {
                  setModalState("회원가입2");
                }}
              >
                NEXT
              </NextButton>
            </SignUp>
          </EmailCheck>
        </Img>
      </Container>
    );
  } else {
    return (
      <Container>
        <Img>
          <Id>
            <FormLabel>아이디</FormLabel>
            <Input />
          </Id>
          <PW>
            <FormLabel>비밀번호</FormLabel>
            <Input />
            <PWCheck>
              <FormLabel>비밀번호 확인</FormLabel>
              <Input />
            </PWCheck>
            <FinishButton>FINISH</FinishButton>
          </PW>
        </Img>
      </Container>
    );
  }
};

export default Login;
