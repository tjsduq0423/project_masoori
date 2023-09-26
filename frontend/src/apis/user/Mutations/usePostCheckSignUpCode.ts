import { postCheckSignUpCode } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { CheckSignUpCodeProps } from "@/types/userType";

const usePostCheckSignUpCode = () => {
  return useMutation(
    (CheckSignUpCodeData: CheckSignUpCodeProps) =>
      postCheckSignUpCode(CheckSignUpCodeData),
    {
      onSuccess: () => {
        console.log("CheckSignUpCode Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostCheckSignUpCode };
