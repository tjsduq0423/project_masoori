import React, { useState, useEffect } from "react";
import Kakao from "@/assets/img/Kakao.png";
import Google from "@/assets/img/Google.png";
import Naver from "@/assets/img/Naver.png";
import BackCards from "@/assets/img/Login.png";
import SignInModalFront from "@/assets/img/signCard/signInModalFront.png";
import SignUpModalFront from "@/assets/img/signCard/signUpModalFront.png";
import styled from "styled-components";
import { usePostLogin } from "@/apis/user/Mutations/usePostLogin";
import {
  CheckDuplicateEmailProps,
  CheckSignUpCodeProps,
  LoginProps,
  SendSignUpCodeProps,
} from "@/types/userType";
import { usePostCheckDuplicateEmail } from "@/apis/user/Mutations/usePostCheckDuplicateEmail";
import { usePostSendSignUpCode } from "@/apis/user/Mutations/usePostSendSignUpCode";
import { usePostCheckSignUpCode } from "@/apis/user/Mutations/usePostCheckSignUpCode";

interface ImgProps {
  loaded: boolean;
}

const Container = styled.div`
  position: absolute;
  left: 39.5%;
  top: 3%;
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
  z-index: 99;
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
  z-index: 3;
  top: 15%;
`;

const PW = styled.div`
  position: relative;
  top: 17%;
  z-index: 3;
`;

const PWCheck = styled.div`
  position: relative;
  top: 7%;
  z-index: 9;
`;
const FormLabel = styled.p`
  font-size: small;
  display: flex;
  font-weight: bold;
  margin-bottom: 2px;
  color: #5e3a66;
`;

const CommentLabel = styled.p<{ viewComment: boolean }>`
  font-size: small;
  display: flex;
  font-weight: bold;
  margin-bottom: 2px;
  color: red;
  opacity: ${(props) => (props.viewComment ? "1" : "0")};
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

const DisableNextButton = styled.button`
  width: 16.275vw;
  border-radius: 5px;
  border-color: #5e3a66;
  border-width: 1px;
  margin-top: 25px;
  height: 30px;
  background-color: #eae2ed;
  color: #5e3a66;
  font-weight: bold;
  font-size: 12px;
`;

const AbleNextButton = styled(DisableNextButton)`
  background-color: #5e3a66;
  color: #eae2ed;
`;

const FinishButton = styled.button`
  width: 16.275vw;
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

const EmailInput = styled.input`
  width: 11.5vw;
  border-radius: 5px;
  border-width: 1px;
  border-color: #5e3a66;
  height: 30px;
`;

const DuplicateCheckButton = styled.button`
  width: 4.5vw;
  border-radius: 5px;
  border-width: 1px;
  border-color: #5e3a66;
  font-size: 12px;
  background-color: #5e3a66;
  height: 30px;
  color: white;
  /* font-weight: bold; */
`;

const DisableSendCodeButton = styled.button`
  width: 4.5vw;
  border-radius: 5px;
  border-width: 1px;
  border-color: #5e3a66;
  font-size: 12px;
  height: 30px;
  font-weight: bold;
  color: #5e3a66;
  background-color: #eae2ed;
`;

const AbleSendCodeButton = styled(DisableSendCodeButton)`
  background-color: #5e3a66;
  color: #eae2ed;
`;

const Login: React.FC = () => {
  const [modalState, setModalState] = useState<string>("로그인");
  const [imageLoaded, setImageLoaded] = useState<boolean>(false); // 이미지 로드 상태
  const [emailCodeComment, setEmailCodeComment] = useState<string>(""); // 이메일 코드 확인 결과 멘트

  //로그인 ~ ----------------------------------------------

  //유저 데이터
  const [userData, setUserData] = useState<LoginProps>({
    email: "",
    password: "",
  });

  //Login에 usePostLogin(API써서 만들어둔 함수들)
  const Login = usePostLogin();

  //변수에 할당하여 나중에 호출한다.
  const handleLogin = () => {
    Login.mutate(userData);
  };

  //~ 로그인  ----------------------------------------------

  //이메일 중복 체크 ~  ----------------------------------------------

  const [isDuplicated, setIsDuplicated] = useState<boolean>(false);
  const [duplicateEmailData, setDuplicateEmailData] =
    useState<CheckDuplicateEmailProps>({
      email: "",
    });

  const CheckDuplicateEmail = usePostCheckDuplicateEmail();

  const handleCheckDuplicateEmail = async () => {
    try {
      const result: boolean =
        await CheckDuplicateEmail.mutateAsync(duplicateEmailData);

      setIsDuplicated(result);

      if (isDuplicated === true) {
        console.log("중복된 이메일입니다.");
      } else {
        console.log("중복되지 않은 이메일입니다.");
      }
    } catch (error) {
      console.error("이메일 중복 확인에 실패했습니다.", error);
    }
  };

  // ~ 이메일 중복 체크 ----------------------------------------------

  //이메일 중복 결과 멘트 ~  ----------------------------------------------

  const [emailComment, setEmailComment] = useState<string>(""); // 이메일 중복확인 결과 멘트
  const [viewComment, setViewComment] = useState<boolean>(false);

  useEffect(() => {
    if (isDuplicated === true) {
      setEmailComment("* 이미 가입한 회원입니다.");
    } else {
      setEmailComment("* 가입 가능한 계정입니다.");
    }
  }, [isDuplicated]);

  // ~ 이메일 중복 결과 멘트 ----------------------------------------------

  //이메일 코드 전송 ~  ----------------------------------------------

  const SendSignUpCode = usePostSendSignUpCode();

  const handleSendSignUpCode = async () => {
    try {
      console.log(duplicateEmailData);
      await SendSignUpCode.mutateAsync(duplicateEmailData);
    } catch (error) {
      console.error("회원가입 코드 전송에 실패했습니다.", error);
    }
  };

  // ~ 이메일 코드 전송 ----------------------------------------------

  //이메일 코드 유효성 검사 ~  ----------------------------------------------

  const [signUpCodeData, setSignUpCodeData] = useState<CheckSignUpCodeProps>({
    email: duplicateEmailData.email,
    code: "",
  });

  const CheckSignUpCode = usePostCheckSignUpCode();

  const handleCheckSignUpCode = async () => {
    try {
      const signupInfo = {
        email: duplicateEmailData.email,
        code: signUpCodeData.code,
      };

      console.log(signupInfo);
      const result = await CheckSignUpCode.mutateAsync(signupInfo);
      console.log(result);
    } catch (error) {
      console.error("회원가입 코드 확인에 실패했습니다.", error);
    }
  };

  // ~ 이메일 코드 유효성 검사 ----------------------------------------------

  useEffect(() => {
    const img = new Image();
    img.src = BackCards;
    img.onload = () => {
      // 이미지   로드가 완료되면 상태 업데이트
      setImageLoaded(true);
    };
  }, []);

  const OAUTH2_REDIERECT_URI = `${process.env.REACT_APP_BASE_URL}/oauth/redirect`; /* 이거에대한페이지 생성 */
  const onSocialButtonClick = (socialName: string) => {
    const AUTH_URL = `${process.env.REACT_APP_SERVER_URL}/login/oauth2/authorization/${socialName}?redirect_uri=${OAUTH2_REDIERECT_URI}`;
    window.location.href = AUTH_URL;
  };

  if (modalState === "로그인") {
    return (
      <Container>
        <LoginFrontImg loaded={imageLoaded}>
          <Id>
            <FormLabel>계정이름</FormLabel>
            <Input
              value={userData.email}
              onChange={(e) =>
                setUserData({ ...userData, email: e.target.value })
              }
            />
          </Id>
          <PW>
            <FormLabel>비밀번호</FormLabel>
            <Input
              value={userData.password}
              type="password"
              onChange={(e) =>
                setUserData({ ...userData, password: e.target.value })
              }
            />
            <SignUp>
              <button
                onClick={() => {
                  setModalState("회원가입1");
                }}
              >
                회원가입
              </button>
            </SignUp>
            <LoginButton onClick={handleLogin}>Login</LoginButton>
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
            <div style={{ display: "flex", justifyContent: "space-between" }}>
              <FormLabel>이메일</FormLabel>
              <CommentLabel viewComment={viewComment}>
                {emailComment}
              </CommentLabel>
            </div>
            <div
              style={{
                display: "flex",
                alignContent: "center",
                justifyContent: "space-between",
                width: "250px",
              }}
            >
              <EmailInput
                value={duplicateEmailData.email}
                onChange={(e) => {
                  setDuplicateEmailData({
                    ...duplicateEmailData,
                    email: e.target.value,
                  });
                  setViewComment(false);
                  setIsDuplicated(false);
                }}
              />
              <DuplicateCheckButton
                onClick={() => {
                  handleCheckDuplicateEmail();
                  setViewComment(true);
                }}
              >
                중복확인
              </DuplicateCheckButton>
            </div>
          </Email>
          <EmailCheck>
            <FormLabel>이메일 코드 확인</FormLabel>
            <div
              style={{
                display: "flex",
                alignContent: "center",
                justifyContent: "space-between",
                width: "250px",
              }}
            >
              <EmailInput //Email 인증 코드
                value={signUpCodeData.code}
                onChange={(e) => {
                  setSignUpCodeData({
                    ...signUpCodeData,
                    code: e.target.value,
                  });
                  console.log(signUpCodeData.code);
                  if (signUpCodeData.code.length === 8) {
                    handleCheckSignUpCode();
                  }
                }}
              />
              {!isDuplicated ? (
                <DisableSendCodeButton>코드전송</DisableSendCodeButton>
              ) : (
                <AbleSendCodeButton
                  onClick={() => {
                    handleSendSignUpCode();
                  }}
                >
                  코드전송
                </AbleSendCodeButton>
              )}
            </div>
            <SignUp>
              <DisableNextButton
                onClick={() => {
                  setModalState("회원가입2");
                }}
              >
                NEXT
              </DisableNextButton>
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
            <FinishButton>FINISH</FinishButton>
          </PW>
        </SignUpFrontImg>
      </Container>
    );
  }
};

export default Login;
