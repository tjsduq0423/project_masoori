import { useQuery } from "@tanstack/react-query";
import { getAllConsume } from "@/apis/dictionary/dictionaryAPI";

const useGetAllConsume = (startDate: string, endDate: string) => {
  const { data } = useQuery(["AllConsume", startDate, endDate], () =>
    getAllConsume(startDate, endDate)
  );
  return data;
};

export { useGetAllConsume };
