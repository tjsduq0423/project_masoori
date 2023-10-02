import { atom } from "recoil";

export const creditInfoState = atom<string>({
  key: "creditInfoState",
  default: "",
});
