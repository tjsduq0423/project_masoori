import React, { useState, useEffect } from "react";
import SignUpModalFront from "@/assets/img/signCard/signUpModalFront.png";
import styled from "styled-components";
// import { useNavigate } from "react-router-dom";
import { usePostSms } from "@/apis/main/Mutations/usePostSms";
import { usePostSmsCheck } from "@/apis/main/Mutations/usePostSmsCheck";
import { usePostConsume } from "@/apis/main/Mutations/usePostConsume";

interface ImgProps {
  loaded: boolean;
}

const Container = styled.div`
  position: absolute;
  left: 39.5%;
  top: 3%;
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
  transition: opacity 0.5s ease-in-out;
`;

const FormLabel = styled.p`
  font-size: small;
  display: flex;
  font-weight: bold;
  margin-bottom: 2px;
  color: #5e3a66;
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

const Email = styled.div`
  position: relative;
  z-index: 1;
  top: 15%;
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
  background-color: #5e3a66;
  color: #eae2ed;
`;

const VerifyNumberModal = () => {
  // const navigate = useNavigate();

  const [imageLoaded, setImageLoaded] = useState<boolean>(false);
  const [name, setName] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [code, setCode] = useState("");

  const usePostSmsMutation = usePostSms();
  const usePostSmsCheckMutation = usePostSmsCheck();
  const usePostConsumeMutation = usePostConsume();

  const postSmsHandler = async (name: string, phonenumber: string) => {
    try {
      // 이름과 전화번호를 사용하여 SMS를 보냅니다.
      await usePostSmsMutation.mutateAsync({
        name: name,
        phoneNumber: phonenumber,
      });

      console.log("인증 코드 전송 성공");
    } catch (error) {
      console.error("인증 코드 전송 실패:", error);
    }
  };

  const postCodeHandler = async (phonenumber: string, code: string) => {
    try {
      await usePostSmsCheckMutation.mutateAsync({
        phoneNumber: phonenumber,
        code: code,
      });

      console.log("인증 코드 전송 성공");
    } catch (error) {
      console.error("인증 코드 전송 실패:", error);
    }
  };

  const postConsumeHandler = async () => {
    try {
      await usePostConsumeMutation.mutateAsync();

      console.log("연동 성공");
    } catch (error) {
      console.error("연동 실패:", error);
    }
  };

  useEffect(() => {
    const img = new Image();
    img.src = SignUpModalFront;
    img.onload = () => {
      setImageLoaded(true);
    };
  }, []);

  return (
    <Container>
      <SignUpFrontImg loaded={imageLoaded}>
        <Email>
          <FormLabel>이름</FormLabel>
          <div
            style={{
              display: "flex",
              alignContent: "center",
              justifyContent: "space-between",
              width: "250px",
            }}
          >
            <EmailInput
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="이름을 입력하세요"
            />
          </div>
        </Email>
        <Email>
          <FormLabel>전화번호</FormLabel>
          <div
            style={{
              display: "flex",
              alignContent: "center",
              justifyContent: "space-between",
              width: "250px",
            }}
          >
            <EmailInput
              value={phonenumber}
              onChange={(e) => setPhonenumber(e.target.value)}
              placeholder="전화번호를 입력하세요"
            />
            <DuplicateCheckButton
              onClick={() => postSmsHandler(name, phonenumber)}
            >
              코드전송
            </DuplicateCheckButton>
          </div>
        </Email>
        <Email>
          <FormLabel>인증번호 확인</FormLabel>
          <div
            style={{
              display: "flex",
              alignContent: "center",
              justifyContent: "space-between",
              width: "250px",
            }}
          >
            <EmailInput
              value={code}
              onChange={(e) => setCode(e.target.value)}
              placeholder="인증번호를 입력하세요"
            />
            <DisableSendCodeButton
              onClick={() => postCodeHandler(phonenumber, code)}
            >
              확인
            </DisableSendCodeButton>
          </div>
          <SignUp>
            <DisableNextButton onClick={() => postConsumeHandler()}>
              연동하기
            </DisableNextButton>
          </SignUp>
        </Email>
      </SignUpFrontImg>
    </Container>
  );
};

export default VerifyNumberModal;
