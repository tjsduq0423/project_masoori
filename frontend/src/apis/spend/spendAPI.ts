import { instance } from "@/apis/instance";
import { toast } from "react-toastify";
import { useRecoilState } from "recoil";
import { nowDateInfoState } from "@/states/spendState";

const postGhost = async () => {
  try {
    const response = await instance.post(`/user/ghost`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postGhost");
  }
};

const postSSESendData = async () => {
  try {
    const response = await instance.post(`/sse/send-data`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postSSESendData");
  }
};

const getSSESubscribe = async () => {
  try {
    const response = await instance.get(`/sse/subscribe`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getSSESubscribe");
  }
};

const getConsumeId = async (id: number) => {
  try {
    const response = await instance.get(`/card/consume/${id}`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - getConsumeId");
  }
};

const getConsumeRecent = async (nowDate: string) => {
  try {
    const response = await instance.get(`/card/consume/recent?now=${nowDate}`);
    return response.data;
  } catch (error: any) {
    // 400 에러 처리 (카드생성중)
    if (
      error.response &&
      error.response.status === 400 &&
      error.response.data.code === "C009"
    ) {
      toast.info("🃏 아직 카드 생성중... 🃏");
    } else if (
      error.response &&
      error.response.status === 400 &&
      error.response.data.code === "C008"
    ) {
      return "인증";
    } else {
      // 다른 에러 처리
      console.error("API 연동 오류 - getConsumeRecent:", error);
      console.log(error.response);
      // 에러 처리 로직 추가
    }
    // 에러를 다시 throw하지 않고 기본값 또는 빈 데이터를 반환
    return null;
  }
};

export {
  postGhost,
  getConsumeId,
  getConsumeRecent,
  postSSESendData,
  getSSESubscribe,
};
