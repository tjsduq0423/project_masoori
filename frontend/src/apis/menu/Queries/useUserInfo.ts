import { useQuery } from "@tanstack/react-query";
import { getUserInfo } from "@/apis/menu/menuAPI";

const useUserInfo = () => {
  const { data } = useQuery(["userInfo"], () => getUserInfo());
  return data;
};

export { useUserInfo };
