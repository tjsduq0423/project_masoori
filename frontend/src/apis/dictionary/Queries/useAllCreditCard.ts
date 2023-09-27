import { useQuery } from "@tanstack/react-query";
import { getAllCreditcard } from "@/apis/dictionary/dictionaryAPI";

const useAllCreditCard = (time: string) => {
  const { data } = useQuery(["allCreditCard", time], () =>
    getAllCreditcard(time)
  );
  return data;
};

export { useAllCreditCard };
