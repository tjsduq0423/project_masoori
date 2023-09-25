import { postSignUp } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { SighUpProps } from "@/types/userType";

const usePostSignUp = () => {
  return useMutation((SignUpData: SighUpProps) => postSignUp(SignUpData));
};

export { usePostSignUp };
