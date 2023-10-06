import { postSpendCardConnect } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostSpendCardConnect = () => {
  return useMutation(() => postSpendCardConnect(), {
    onSuccess: () => {
      console.log("SpendCardConnect Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostSpendCardConnect };
