import { atom } from "recoil";

interface LuckInfo {
  name: string;
  summary: string;
  imagePath: string;
  description: string;
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: LuckInfo = {
  name: "",
  summary: "",
  imagePath: "",
  description: "",
};

export const luckInfoState = atom<LuckInfo>({
  key: "luckInfoState",
  default: defaultUserInfo,
});
