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
    const response = await instance.get(`/user/alram`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postAlarm");
  }
};

const getUserInfo = async () => {
  try {
    const response = await instance.get(`/user/info`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getUserInfo");
  }
};

const postProfileImage = async (id: number) => {
  try {
    const response = await instance.post(`/card/profileimage`, {
      id: id,
    });
    return response.data;
  } catch {
    new Error("api 연동 오류 - postProfileImage");
  }
};

export { getFaqData, postAlarm, getUserInfo, postProfileImage };
