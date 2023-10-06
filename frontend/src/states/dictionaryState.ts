import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const creditInfoState = atom<string>({
  key: "creditInfoState",
  default: "",
  effects_UNSTABLE: [persistAtom],
});

export const spendIdState = atom<number>({
  key: "spendIdState",
  default: 1,
  effects_UNSTABLE: [persistAtom],
});

export const specialIdState = atom<number | null>({
  key: "specialIdState",
  default: null,
  effects_UNSTABLE: [persistAtom],
});

export const specialImageUrlState = atom<string>({
  key: "specialImageUrlState",
  default: "",
  effects_UNSTABLE: [persistAtom],
});
