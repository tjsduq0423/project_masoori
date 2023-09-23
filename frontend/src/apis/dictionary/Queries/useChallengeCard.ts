import { useQuery } from "@tanstack/react-query";
import { getChallengeCard } from "@/apis/dictionary/dictionaryAPI";

const useChallengeCard = () => {
  const { data } = useQuery(["challengeCard"], () => getChallengeCard());
  return data;
};

export { useChallengeCard };
