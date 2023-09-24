import { useQuery } from "@tanstack/react-query";
import { getAllCreditcard } from "@/apis/dictionary/dictionaryAPI";

const useAllChallengeCard = () => {
  const { data } = useQuery(["allCreditCard"], () => getAllCreditcard());
  return data;
};

export { useAllChallengeCard };
