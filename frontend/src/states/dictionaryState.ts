import { atom } from "recoil";

export const creditInfoState = atom<string>({
  key: "creditInfoState",
  default: "",
});

export const spendIdState = atom<number>({
  key: "spendIdState",
  default: 1,
});
