import { useQuery } from "@tanstack/react-query";
import { getFortune } from "@/apis/luck/luckAPI";

const useFortune = () => {
  const { data } = useQuery(["fortune"], () => getFortune());
  return data;
};

export { useFortune };
