import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

interface LuckInfo {
  name: string;
  summary: string;
  imagePath: string;
  description: string;
}

interface ColorInfo {
  color: string;
  colorName: string;
  description: string;
  imagePath: string;
}

// userInfoState의 기본값에 대한 타입 정의
const defaultUserInfo: LuckInfo = {
  name: "",
  summary: "",
  imagePath: "",
  description: "",
};

const defaultColorInfo: ColorInfo = {
  color: "",
  colorName: "",
  description: "",
  imagePath: "",
};

export const luckInfoState = atom<LuckInfo>({
  key: "luckInfoState",
  default: defaultUserInfo,
  effects_UNSTABLE: [persistAtom],
});

export const ColorInfoState = atom<ColorInfo>({
  key: "colorInfoState",
  default: defaultColorInfo,
  effects_UNSTABLE: [persistAtom],
});
