import { atom } from "recoil";

interface UserInfo {
  userId: number;
  username: string;
  email: string;
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: UserInfo = {
  userId: 0,
  username: "",
  email: "",
};

export const userInfoState = atom<UserInfo>({
  key: "userInfoState",
  default: defaultUserInfo,
});
