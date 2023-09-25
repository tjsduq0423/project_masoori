import { instance } from "@/apis/instance";

// 사용자 최초 등록 시 소비카드를 생성한다.
const postConsume = async () => {
  try {
    const response = await instance.post(`/card/consume`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postConsume");
  }
};

const postGhost = async () => {
  try {
    const response = await instance.post(`/user/ghost`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postGhost");
  }
};

export { postConsume, postGhost };
