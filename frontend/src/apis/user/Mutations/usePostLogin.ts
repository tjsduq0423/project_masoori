import { postLogin } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { LoginProps } from "@/types/userType";

const usePostLogin = () => {
  return useMutation((LoginData: LoginProps) => postLogin(LoginData), {
    onSuccess: () => {
      console.log("Login Success");
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { usePostLogin };
