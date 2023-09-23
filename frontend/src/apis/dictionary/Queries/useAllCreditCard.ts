import { useQuery } from "@tanstack/react-query";
import { getAllCreditcard } from "../dictionaryAPI";

const useAllCreditCard = () => {
  const { data } = useQuery(["AllCreditCard"], () => getAllCreditcard());
  return data;
};

export { useAllCreditCard };
