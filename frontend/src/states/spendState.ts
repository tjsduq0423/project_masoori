import {
  SSEInfo,
  SpendInfo,
  UserSms,
  UserSmsCheck,
  UserVerifyInfo,
} from "@/types/spendType";
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const userSmsInfoState = atom<UserSms>({
  key: "userSmsInfoState",
  default: {} as UserSms,
  effects_UNSTABLE: [persistAtom],
});

export const userSmsCheckInfoState = atom<UserSmsCheck>({
  key: "userSmsCheckInfoState",
  default: {} as UserSmsCheck,
  effects_UNSTABLE: [persistAtom],
});

export const userVerifyInfoState = atom<UserVerifyInfo>({
  key: "userVerifyInfoState",
  default: {} as UserVerifyInfo,
  effects_UNSTABLE: [persistAtom],
});

export const nowDateInfoState = atom<string>({
  key: "nowDateInfoState",
  default: "",
  effects_UNSTABLE: [persistAtom],
});

export const spendInfoState = atom<SpendInfo>({
  key: "spendInfoState",
  default: {
    card: {
      id: 0,
      name: "",
      imagePath: "",
      description: "",
      createdDate: new Date(),
      cardType: "",
    },
    basicList: [
      {
        id: 0,
        keyword: "",
        totalAmount: 0,
        frequency: 0,
      },
    ],
  },
  effects_UNSTABLE: [persistAtom],
});

export const SSEInfoState = atom<SSEInfo>({
  key: "SSEInfoState",
  default: {
    timeout: 0,
  },
  effects_UNSTABLE: [persistAtom],
});

export const settingModalOpenState = atom<boolean>({
  key: "settingModalOpenState",
  default: false,
  effects_UNSTABLE: [persistAtom],
});
