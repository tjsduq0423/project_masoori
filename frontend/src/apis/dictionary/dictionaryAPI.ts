import { instance } from "@/apis/instance";
import { AllChallengeCardProps } from "@/types/dictionaryType";

// 사용자 추천 카드 리스트 조회 API
const getAllCreditcard = async () => {
  try {
    const response = await instance.get(`/creditcard`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAllCreditcard");
  }
};

// 카드 상세 정보 조회 조회 API
const getCreditcard = async (cardId: number) => {
  try {
    const response = await instance.get(`/creditcard/${cardId}`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getCreditcard");
  }
};

// 월간 소비 분석 API
const getAnalyticsMonth = async () => {
  try {
    const response = await instance.get(`/analytics/month`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAnalyticsMonth");
  }
};

// 조회 시작일과 종료일을 통해 유저의 챌린지 카드들을 조회
const getAllChallengeCard = async (challengeDate: AllChallengeCardProps) => {
  try {
    const response = await instance.get(`/card/challengecard`, {
      params: challengeDate,
    });
    return response.data;
  } catch {
    new Error("api 연동 오류 - getAllChallengeCard");
  }
};

// 유저에게 할당되어 있는 챌린지카드와 챌린지를 연,월일을 통해 조회한다.
const getChallengeCard = async (id: number) => {
  try {
    const response = await instance.get(`/card/challenge/${id}`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getChallengeCard");
  }
};

const getConsume = async (id: number) => {
  try {
    const response = await instance.get(`/card/consume/${id}`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getConsume");
  }
};

export {
  getAllCreditcard,
  getCreditcard,
  getAnalyticsMonth,
  getChallengeCard,
  getAllChallengeCard,
  getConsume,
};
