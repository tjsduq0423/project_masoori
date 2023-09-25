import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postGhost } from "@/apis/spend/spendAPI";

const usePostGhost = () => {
  const queryClient = useQueryClient();

  return useMutation(() => postGhost(), {
    onSuccess: () => {
      queryClient.invalidateQueries(["ghost"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostGhost };
