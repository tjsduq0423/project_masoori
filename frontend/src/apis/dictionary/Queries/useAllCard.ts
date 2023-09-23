import { useQuery } from "@tanstack/react-query";
import { getAllCard } from "@/apis/dictionary/dictionaryAPI";

const useAllCard = () => {
  const { data } = useQuery(["allCard"], () => getAllCard());
  return data;
};

export { useAllCard };
