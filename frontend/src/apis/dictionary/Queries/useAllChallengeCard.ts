import { useQuery } from "@tanstack/react-query";
import { getAllChallengeCard } from "@/apis/dictionary/dictionaryAPI";

const useAllChallengeCard = (startDate: string, endDate: string) => {
  const { data } = useQuery(["allChallengeCard", startDate, endDate], () =>
    getAllChallengeCard(startDate, endDate)
  );
  return data;
};

export { useAllChallengeCard };
