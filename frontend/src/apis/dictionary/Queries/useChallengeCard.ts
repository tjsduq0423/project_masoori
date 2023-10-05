import { useQuery } from "@tanstack/react-query";
import { getChallengeCard } from "@/apis/dictionary/dictionaryAPI";

const useChallengeCard = (id: number | null) => {
  const { data } = useQuery(["challengeCard", id], () => getChallengeCard(id), {
    enabled: id !== null, // id가 null이 아닌 경우에만 요청을 활성화
  });
  return data;
};

export { useChallengeCard };
