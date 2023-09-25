import React, { useState, useEffect } from "react";
import Kakao from "@/assets/img/Kakao.png";
import Google from "@/assets/img/Google.png";
import Naver from "@/assets/img/Naver.png";
import BackCards from "@/assets/img/Login.png";
import SignInModalFront from "@/assets/img/signCard/signInModalFront.png";
import SignUpModalFront from "@/assets/img/signCard/signUpModalFront.png";
import styled from "styled-components";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";
import { userInfoState } from "@/states/userState";

interface ImgProps {
  loaded: boolean;
}

const Container = styled.div`
  position: absolute;
  left: 39.5%;
  top: 0.5%;
`;

const LoginFrontImg = styled.div<ImgProps>`
  position: absolute;
  left: 40%;
  height: 65vh;
  width: 21vw;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  background-image: url(${SignInModalFront});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  opacity: ${({ loaded }) => (loaded ? "1" : "0")};
`;

const SignUpFrontImg = styled.div<ImgProps>`
  position: absolute;
  left: 40%;
  height: 65vh;
  width: 21vw;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  background-image: url(${SignUpModalFront});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  opacity: ${({ loaded }) => (loaded ? "1" : "0")};
  transition: opacity 0.5s ease-in-out;
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
  font-size: small;
  display: flex;
  font-weight: bold;
  margin-bottom: 2px;
  color: #5e3a66;
`;

const Input = styled.input`
  width: 17vw;
  border-radius: 5px;
  border-width: 1px;
  border-color: #5e3a66;
  height: 30px;
`;

const SignUp = styled.div`
  margin-top: 5px;
  margin-bottom: 5px;
  display: flex;
  justify-content: end;
  font-weight: bold;
  font-size: small;
  color: #5e3a66;
`;

const LoginButton = styled.button`
  width: 17vw;
  border-radius: 5px;
  border-color: #5e3a66;
  border-width: 2px;
  margin-bottom: 20px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: 12px;
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
  border-color: #5e3a66;
  border-width: 2px;
  margin-top: 25px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: 12px;
`;

const Login: React.FC = () => {
  const [modalState, setModalState] = useState<string>("로그인");
  const [imageLoaded, setImageLoaded] = useState<boolean>(false); // 이미지 로드 상태

  useEffect(() => {
    const img = new Image();
    img.src = BackCards;
    img.onload = () => {
      // 이미지 로드가 완료되면 상태 업데이트
      setImageLoaded(true);
    };
  }, []);

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
        <LoginFrontImg loaded={imageLoaded}>
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
        </LoginFrontImg>
      </Container>
    );
  } else if (modalState === "회원가입1") {
    return (
      <Container>
        <SignUpFrontImg loaded={imageLoaded}>
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
        </SignUpFrontImg>
      </Container>
    );
  } else {
    return (
      <Container>
        <SignUpFrontImg loaded={imageLoaded}>
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
            <NextButton>FINISH</NextButton>
          </PW>
        </SignUpFrontImg>
      </Container>
    );
  }
};

export default Login;
