import { instance } from "@/apis/instance";

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
      alert("카드 생성 중입니다. error code : C009");
      console.error("400 에러가 발생했습니다.");
    } else if (
      error.response &&
      error.response.status === 400 &&
      error.response.data.code === "C008"
    ) {
      alert("카드 생성 중입니다. error code : C008");
      console.error("400 에러가 발생했습니다.");
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
