import { useQuery } from "@tanstack/react-query";
import { getAnalyticsMonth } from "@/apis/dictionary/dictionaryAPI";

const useAnalyticsMonth = () => {
  const { data } = useQuery(["analyticsMonth"], () => getAnalyticsMonth());
  return data;
};

export { useAnalyticsMonth };
