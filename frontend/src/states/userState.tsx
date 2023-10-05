import { atom } from "recoil";

import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const modalOpenState = atom<boolean>({
  key: "modalOpenState",
  default: false,
  effects_UNSTABLE: [persistAtom],
});
