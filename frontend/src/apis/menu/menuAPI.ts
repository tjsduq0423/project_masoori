import { instance } from "@/apis/instance";

// FAQ 데이터 관련 API
const getFaqData = async () => {
  try {
    const response = await instance.get(`/faq`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getFaqData");
  }
};

const postAlarm = async () => {
  try {
    const response = await instance.get(`/api/user/alram`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postAlarm");
  }
};

const getUserInfo = async () => {
  try {
    const response = await instance.get(`/api/user/info`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getUserInfo");
  }
};

export { getFaqData, postAlarm, getUserInfo };
