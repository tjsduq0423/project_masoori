import { atom } from "recoil";

interface UserInfo {
  imagePath: string;
  smsAlarm: boolean;
  cardGeneration: boolean;
  dailySpending: number;
  weeklySpending: number;
  monthlySpending: number;
  isAuthenticated: boolean;
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: UserInfo = {
  imagePath: "",
  smsAlarm: false,
  cardGeneration: false,
  dailySpending: 0,
  weeklySpending: 0,
  monthlySpending: 0,
  isAuthenticated: false,
};

export const userInfoState = atom<UserInfo>({
  key: "userInfoState",
  default: defaultUserInfo,
});

/* 
import { useUserInfo } from "@/apis/menu/Queries/useUserInfo";

const [user, setUser] = useRecoilState(userState);
  const userInfo = useUserInfo();
*/
