import { instance } from "@/apis/instance";

// 사용자 금전운 전체 조회 API
const getAllUserFortune = async () => {
  try {
    const response = await instance.get(`/api/v1/lucky/userfortune`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAllUserFortune");
  }
};

// 사용자 금전운 조회 API
const getUserFortune = async () => {
  try {
    const response = await instance.get(`/api/v1/lucky/user/fortune`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getUserFortune");
  }
};

// 비로그인 사용자 금전운 조회 API
const getFortune = async () => {
  try {
    const response = await instance.get(`/api/v1/lucky/fortune`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getFortune");
  }
};

// 행운의 색 조회 API
const getLuckyColor = async () => {
  try {
    const response = await instance.get(`/api/v1/lucky/color`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getLuckyColor");
  }
};

export { getAllUserFortune, getUserFortune, getFortune, getLuckyColor };
