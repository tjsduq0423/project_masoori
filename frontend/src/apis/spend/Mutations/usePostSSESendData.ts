import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postSSESendData } from "../spendAPI";

const usePostSSESendData = () => {
  const queryClient = useQueryClient();

  return useMutation(() => postSSESendData(), {
    onSuccess: () => {
      queryClient.invalidateQueries(["postSSESendData"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostSSESendData };
