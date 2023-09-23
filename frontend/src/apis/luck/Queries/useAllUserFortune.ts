import { useQuery } from "@tanstack/react-query";
import { getAllUserFortune } from "@/apis/luck/luckAPI";

const useAllUserFortune = () => {
  const { data } = useQuery(["allUserFortune"], () => getAllUserFortune());
  return data;
};

export { useAllUserFortune };
