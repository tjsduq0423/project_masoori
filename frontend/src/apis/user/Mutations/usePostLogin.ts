import { postLogin } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { LoginProps } from "@/types/userType";

const usePostLogin = () => {
  return useMutation((LoginData: LoginProps) => postLogin(LoginData));
};

export { usePostLogin };
