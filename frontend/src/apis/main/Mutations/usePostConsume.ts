import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postConsume } from "@/apis/main/mainAPI";

const usePostConsume = () => {
  const queryClient = useQueryClient();

  return useMutation(() => postConsume(), {
    onSuccess: () => {
      queryClient.invalidateQueries(["consume"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostConsume };
