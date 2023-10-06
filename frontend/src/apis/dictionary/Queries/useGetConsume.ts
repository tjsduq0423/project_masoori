import { useQuery } from "@tanstack/react-query";
import { getConsume } from "@/apis/dictionary/dictionaryAPI";

const useGetConsume = (id: number) => {
  const { data } = useQuery(["Consume", id], () => getConsume(id));
  return data;
};

export { useGetConsume };
