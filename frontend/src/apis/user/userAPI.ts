import { instance } from "@/apis/instance";
import {
  CheckPhoneSMSProps,
  SendPhoneSMSProps,
  LoginProps,
  SendSignUpCodeProps,
  RenewPasswordProps,
  CheckSignUpCodeProps,
  CheckDuplicateEmailProps,
  SignUpProps,
  SettingMonthlyGoalProps,
} from "@/types/userType";
import { error } from "console";

//
const postSendPhoneSMS = async (SendPhoneSMSData: SendPhoneSMSProps) => {
  try {
    const response = await instance.post(`/user/sms`, SendPhoneSMSData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postSendPhoneSMS"));
  }
};

const postCheckPhoneSMS = async (CheckPhoneSMSData: CheckPhoneSMSProps) => {
  try {
    const response = await instance.post(`/user/sms/check`, CheckPhoneSMSData);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postCheckPhoneSMS"));
  }
};

const postSignUp = async (SignUpData: SignUpProps) => {
  try {
    const response = await instance.post(`/user/signup`, SignUpData);
    console.log(response);
    return response.status;
  } catch {
    console.log(new Error("api 연동 오류 - postSignUp"));
  }
};

const postLogout = async () => {
  try {
    const response = await instance.post(`/user/logout`);
    return response;
  } catch {
    console.log(new Error("api 연동 오류 - postLogout"));
  }
};

const postLogin = async (LoginData: LoginProps) => {
  try {
    const response = await instance.post(`/user/login`, LoginData);
    console.log(response);
    localStorage.setItem("accessToken", response.data.accessToken!);

    return response;
  } catch {
    console.log(new Error("api 연동 오류 - postLogin"));
  }
};

const postSpendCardConnect = async () => {
  try {
    const response = await instance.post(`/user/generation`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postSpendCardConnect"));
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
    new Error("api 연동 오류 - postSendSignUpCode");
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
    console.log(new Error("api 연동 오류 - postRenewPassword"));
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
    console.log(response);
    return response.status;
  } catch {
    console.log(new Error("api 연동 오류 - postCheckSignUpCode"));
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
    console.log(new Error("api 연동 오류 - postCheckDuplicateEmail"));
  }
};

const postGeneration = async () => {
  try {
    const response = await instance.post(`/user/generation`);
    console.log(response);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postGeneration"));
  }
};

const postAlram = async () => {
  try {
    const response = await instance.post(`/user/alram`);
    console.log(response);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postAlram"));
  }
};

const postSetGoalAmount = async (amount: SettingMonthlyGoalProps) => {
  try {
    const response = await instance.post(`/user/monthly-spending`, amount);
    console.log(response);
    return response;
  } catch {
    console.log(new Error("api 연동 오류 - postSetGoalAmount"));
  }
};

export {
  postSetGoalAmount,
  postAlram,
  postGeneration,
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
