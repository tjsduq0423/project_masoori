import { useQuery } from "@tanstack/react-query";
import { getSSESubscribe } from "../spendAPI";

const useGetSSESubscribe = () => {
  const { data } = useQuery(["getSSESubscribe"], () => getSSESubscribe());
  return data;
};

export { useGetSSESubscribe };
