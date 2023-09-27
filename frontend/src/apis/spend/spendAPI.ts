import { instance } from "@/apis/instance";

const postGhost = async () => {
  try {
    const response = await instance.post(`/user/ghost`);
    return response.data;
  } catch {
    new Error("api 연동 오류 - postGhost");
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

export { postGhost, getConsumeId };
