import { useQuery } from "@tanstack/react-query";
import { getCreditcard } from "@/apis/dictionary/dictionaryAPI";

const useCreditCard = (cardId: number) => {
  const { data } = useQuery(["creditCard", cardId], () =>
    getCreditcard(cardId)
  );
  return data;
};

export { useCreditCard };
