import { postCheckSignUpCode } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { CheckSignUpCodeProps } from "@/types/userType";

const usePostCheckSignUpCode = () => {
  return useMutation((CheckSignUpCodeData: CheckSignUpCodeProps) =>
    postCheckSignUpCode(CheckSignUpCodeData)
  );
};

export { usePostCheckSignUpCode };
