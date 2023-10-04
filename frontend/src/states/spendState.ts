import {
  SSEInfo,
  SpendInfo,
  UserSms,
  UserSmsCheck,
  UserVerifyInfo,
} from "@/types/spendType";
import { atom } from "recoil";

export const userSmsInfoState = atom<UserSms>({
  key: "userSmsInfoState",
  default: {} as UserSms,
});

export const userSmsCheckInfoState = atom<UserSmsCheck>({
  key: "userSmsCheckInfoState",
  default: {} as UserSmsCheck,
});

export const userVerifyInfoState = atom<UserVerifyInfo>({
  key: "userVerifyInfoState",
  default: {} as UserVerifyInfo,
});

export const nowDateInfoState = atom<string>({
  key: "nowDateInfoState",
  default: "",
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
});

export const SSEInfoState = atom<SSEInfo>({
  key: "SSEInfoState",
  default: {
    timeout: 0,
  },
});

export const settingModalOpenState = atom<boolean>({
  key: "settingModalOpenState",
  default: false,
});
