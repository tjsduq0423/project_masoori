import { useQuery } from "@tanstack/react-query";
import { getAllChallengeCard } from "@/apis/dictionary/dictionaryAPI";
import { AllChallengeCardProps } from "@/types/dictionaryType";

const useAllChallengeCard = (challengeDate: AllChallengeCardProps) => {
  const { data } = useQuery(["allChallengeCard", challengeDate], () =>
    getAllChallengeCard(challengeDate)
  );
  return data;
};

export { useAllChallengeCard };
