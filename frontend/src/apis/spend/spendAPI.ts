import { instance } from "@/apis/instance";

// 사용자 추천 카드 리스트 조회 API
const getAllCreditcard = async () => {
  try {
    const response = await instance.get(`/api/creditcard`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAllCreditcard");
  }
};

// 카드 상세 정보 조회 조회 API
const getCreditcard = async () => {
  try {
    const response = await instance.get(`/api/creditcard/{cardId}`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getCreditcard");
  }
};

// 월간 소비 분석 API
const getAnalyticsMonth = async () => {
  try {
    const response = await instance.get(`/api/v1/analytics/month`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAnalyticsMonth");
  }
};

const postConsume = async () => {
  try {
    const response = await instance.get(`/api/v1/card/consume`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postConsume");
  }
};

export { getAllCreditcard, getCreditcard, getAnalyticsMonth, postConsume };
