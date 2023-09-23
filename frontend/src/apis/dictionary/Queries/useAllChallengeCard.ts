import { useQuery } from "@tanstack/react-query";
import { getAllChallengeCard } from "@/apis/dictionary/dictionaryAPI";

const useAllChallengeCard = () => {
  const { data } = useQuery(["allChallengeCard"], () => getAllChallengeCard());
  return data;
};

export { useAllChallengeCard };
