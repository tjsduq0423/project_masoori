import { atom } from "recoil";

interface UserInfo {
  email: string;
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: UserInfo = {
  email: "",
};

export const userInfoState = atom<UserInfo>({
  key: "userInfoState",
  default: defaultUserInfo,
});
