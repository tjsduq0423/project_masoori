import { useQuery } from "@tanstack/react-query";
import { getUserInfo } from "@/apis/menu/menuAPI";

const useUserInfo = (isLogin: string) => {
  const { data } = useQuery(
    ["userInfo"],
    () => {
      return getUserInfo();
    },
    {
      enabled: Boolean(isLogin),
      suspense: false,
    }
  );
  return data;
};

export { useUserInfo };
