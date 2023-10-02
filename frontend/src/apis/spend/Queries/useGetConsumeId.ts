import { useQuery } from "@tanstack/react-query";
import { getConsumeId } from "@/apis/spend/spendAPI";

const useGetConsumeId = (cardId: number) => {
  const { data } = useQuery(["consumeId", cardId], () => getConsumeId(cardId));
  return data;
};

export { useGetConsumeId };
