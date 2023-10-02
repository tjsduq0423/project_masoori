import { useQuery } from "@tanstack/react-query";
import { getConsumeRecent } from "@/apis/spend/spendAPI";

const useGetConsumeRecent = (nowDate: string) => {
  const { data } = useQuery(
    ["consumeRecent", nowDate],
    () => {
      return getConsumeRecent(nowDate);
    },
    {
      enabled: Boolean(nowDate),
      suspense: false,
    }
  );
  return data;
};

export { useGetConsumeRecent };
