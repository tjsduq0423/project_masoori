import { useQuery } from "@tanstack/react-query";
import { getUserFortune } from "@/apis/luck/luckAPI";

const useUserFortune = () => {
  const { data } = useQuery(["userFortune"], () => getUserFortune());
  return data;
};

export { useUserFortune };
