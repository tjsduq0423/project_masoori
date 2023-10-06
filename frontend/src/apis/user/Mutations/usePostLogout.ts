import { postLogout } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostLogout = () => {
  return useMutation(() => postLogout(), {
    onSuccess: () => {
      console.log("Logout Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostLogout };
