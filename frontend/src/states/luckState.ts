import { atom } from "recoil";

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
});

export const ColorInfoState = atom<ColorInfo>({
  key: "colorInfoState",
  default: defaultColorInfo,
});
