import { postAlram } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostAlram = () => {
  return useMutation(() => postAlram(), {
    onSuccess: () => {
      console.log("Alram Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostAlram };
