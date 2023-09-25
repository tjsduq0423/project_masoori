import { useQuery } from "@tanstack/react-query";
import { getConsume } from "@/apis/spend/spendAPI";

const useGetConsume = (id: number) => {
  const { data } = useQuery(["Consume", id], () => getConsume(id));
  return data;
};

export { useGetConsume };
