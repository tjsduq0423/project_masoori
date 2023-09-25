import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postAlarm } from "@/apis/menu/menuAPI";

const useAlarm = () => {
  const queryClient = useQueryClient();

  return useMutation(() => postAlarm(), {
    onSuccess: () => {
      queryClient.invalidateQueries(["alarm"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useAlarm };
