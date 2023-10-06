import { useQuery } from "@tanstack/react-query";
import { getConsumeRecent } from "@/apis/spend/spendAPI";

const useGetConsumeRecent = (nowDate: string) => {
  // Create a query key that depends on nowDate
  const queryKey = ["consumeRecent", nowDate];

  // Define a function to fetch data
  const fetchData = () => {
    return getConsumeRecent(nowDate);
  };

  // Use the useQuery hook with the queryKey
  const { data } = useQuery(queryKey, fetchData, {
    enabled: Boolean(nowDate), // Only enable the query when nowDate is truthy
    suspense: false,
  });

  return data;
};

export { useGetConsumeRecent };
