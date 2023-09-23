import { instance } from "@/apis/instance";

// 유저의 모든 소비카드 조회, 연,월을 기준으로 페이지네이션
const getAllCard = async () => {
  try {
    const response = await instance.get(`/api/v1/card/consume`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAllCard");
  }
};

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

// 조회 시작일과 종료일을 통해 유저의 챌린지 카드들을 조회
const getAllChallengeCard = async () => {
  try {
    const response = await instance.get(`/api/v1/card/challengecard`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAnalyticsMonth");
  }
};

// 유저에게 할당되어 있는 챌린지카드와 챌린지를 연,월일을 통해 조회한다.
const getChallengeCard = async () => {
  try {
    const response = await instance.get(`/api/v1/card/challenge`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAnalyticsMonth");
  }
};

export {
  getAllCard,
  getAllCreditcard,
  getCreditcard,
  getAnalyticsMonth,
  getChallengeCard,
  getAllChallengeCard,
};
