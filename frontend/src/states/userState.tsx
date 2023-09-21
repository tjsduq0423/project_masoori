import { atom } from "recoil";

interface UserInfo {
  userId: number;
  username: string;
  email: string;
  // 필요한 다른 속성들을 여기에 추가할 수 있습니다.
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: UserInfo = {
  userId: 0,
  username: "",
  email: "",
  // 기본값의 다른 속성들을 여기에 추가할 수 있습니다.
};

export const userInfoState = atom<UserInfo>({
  key: "userInfoState",
  default: defaultUserInfo,
});
