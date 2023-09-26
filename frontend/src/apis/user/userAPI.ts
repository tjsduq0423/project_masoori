import { instance } from "@/apis/instance";
import {
  CheckPhoneSMSProps,
  SendPhoneSMSProps,
  SighUpProps,
  LoginProps,
  SendSignUpCodeProps,
  RenewPasswordProps,
  CheckSignUpCodeProps,
  CheckDuplicateEmailProps,
} from "@/types/userType";

//
const postSendPhoneSMS = async (SendPhoneSMSData: SendPhoneSMSProps) => {
  try {
    const response = await instance.post(`/user/sms`, SendPhoneSMSData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postCheckPhoneSMS = async (CheckPhoneSMSData: CheckPhoneSMSProps) => {
  try {
    const response = await instance.post(`/user/sms/check`, CheckPhoneSMSData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postSignUp = async (SignUpData: SighUpProps) => {
  try {
    const response = await instance.post(`/user/signup`, SignUpData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postLogout = async () => {
  try {
    const response = await instance.post(`/user/logout`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postLogin = async (LoginData: LoginProps) => {
  try {
    const response = await instance.post(`/user/login`, LoginData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postSpendCardConnect = async () => {
  try {
    const response = await instance.post(`/user/generation`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postSendSignUpCode = async (SendSignUpCodeData: SendSignUpCodeProps) => {
  try {
    const response = await instance.post(
      `/user/email/signup`,
      SendSignUpCodeData
    );
    console.log(response);
    // return response.data;
  } catch {
    new Error("api 연동 오류 - postConsume");
  }
};

const postRenewPassword = async (RenewPasswordData: RenewPasswordProps) => {
  try {
    const response = await instance.post(
      `/user/email/password`,
      RenewPasswordData
    );
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postCheckSignUpCode = async (
  CheckSignUpCodeData: CheckSignUpCodeProps
) => {
  try {
    const response = await instance.post(
      `/user/email/check`,
      CheckSignUpCodeData
    );
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

const postCheckDuplicateEmail = async (
  CheckDuplicateEmailData: CheckDuplicateEmailProps
) => {
  try {
    const response = await instance.post(
      `/user/email-duplication/check`,
      CheckDuplicateEmailData
    );
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postConsume"));
  }
};

export {
  postCheckDuplicateEmail,
  postSendPhoneSMS,
  postCheckPhoneSMS,
  postSignUp,
  postLogout,
  postLogin,
  postSpendCardConnect,
  postSendSignUpCode,
  postRenewPassword,
  postCheckSignUpCode,
};
