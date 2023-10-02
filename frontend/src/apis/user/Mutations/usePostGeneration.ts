import { postGeneration } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostGeneration = () => {
  return useMutation(() => postGeneration(), {
    onSuccess: () => {
      console.log("Generation Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostGeneration };
