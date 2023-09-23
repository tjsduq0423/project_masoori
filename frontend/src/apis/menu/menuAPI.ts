import { instance } from "@/apis/instance";

// FAQ 데이터 관련 API
const getFaqData = async () => {
  try {
    const response = await instance.get(`/api/faq'`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getFaqData");
  }
};

export { getFaqData };
