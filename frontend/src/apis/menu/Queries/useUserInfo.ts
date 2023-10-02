import { useQuery } from "@tanstack/react-query";
import { getUserInfo } from "@/apis/menu/menuAPI";

const useUserInfo = (loginBoolean: boolean) => {
  const { data } = useQuery(
    ["userInfo"],
    () => {
      return getUserInfo();
    },
    {
      enabled: Boolean(loginBoolean),
      suspense: false,
    }
  );
  return data;
};

export { useUserInfo };
