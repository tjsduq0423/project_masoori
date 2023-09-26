import { postSignUp } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { SighUpProps } from "@/types/userType";

const usePostSignUp = () => {
  return useMutation((SignUpData: SighUpProps) => postSignUp(SignUpData), {
    onSuccess: () => {
      console.log("SignUp Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostSignUp };
