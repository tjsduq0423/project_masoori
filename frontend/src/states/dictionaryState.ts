import { atom } from "recoil";

export const creditInfoState = atom<string>({
  key: "creditInfoState",
  default: "",
});

export const spendIdState = atom<number>({
  key: "spendIdState",
  default: 1,
});

export const specialIdState = atom<number | null>({
  key: "specialIdState",
  default: null,
});

export const specialImageUrlState = atom<string>({
  key: "specialImageUrlState",
  default: "",
});
