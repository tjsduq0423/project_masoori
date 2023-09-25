import { useQuery } from "@tanstack/react-query";
import { getFaqData } from "@/apis/menu/menuAPI";

const useFaqData = () => {
  const { data } = useQuery(["faqData"], () => getFaqData());
  return data;
};

export { useFaqData };
