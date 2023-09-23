import { useQuery } from "@tanstack/react-query";
import { getCreditcard } from "@/apis/dictionary/dictionaryAPI";

const useCreditCard = () => {
  const { data } = useQuery(["creditCard"], () => getCreditcard());
  return data;
};

export { useCreditCard };
