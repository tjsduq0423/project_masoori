import { useQuery } from "@tanstack/react-query";
import { getChallengeCard } from "@/apis/dictionary/dictionaryAPI";

const useChallengeCard = (id: number) => {
  const { data } = useQuery(["challengeCard", id], () => getChallengeCard(id));
  return data;
};

export { useChallengeCard };
