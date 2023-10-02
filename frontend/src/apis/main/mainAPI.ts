import { instance } from "@/apis/instance";
import { UserSms, UserSmsCheck } from "@/types/spendType";

// 입력된 사용자 정보를 업데이트하고 해당 휴대폰번호로 인증코드를 발송한다.
const postSms = async (userSms: UserSms) => {
  try {
    await instance.post(`/user/sms`, userSms);
    console.log(userSms);
    console.log("api 연동 완료 - postSms");
  } catch {
    new Error("api 연동 오류 - postSms");
  }
};

// 사용자가 입력한 인증코드와 서버에 저장된 인증코드가 일치하는 지 확인한다.
const postSmsCheck = async (userSmsCheck: UserSmsCheck) => {
  try {
    const response = await instance.post(`/user/sms/check`, userSmsCheck);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postSmsCheck");
  }
};

// 사용자 최초 등록 시 소비카드를 생성한다.
const postConsume = async () => {
  try {
    const response = await instance.post(`/card/consume`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postConsume");
  }
};

export { postSms, postSmsCheck, postConsume };
