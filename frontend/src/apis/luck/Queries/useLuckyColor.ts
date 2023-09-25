import { useQuery } from "@tanstack/react-query";
import { getLuckyColor } from "@/apis/luck/luckAPI";

const useLuckyColor = () => {
  const { data } = useQuery(["luckyColor"], () => getLuckyColor());
  return data;
};

export { useLuckyColor };
