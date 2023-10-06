import { postCheckDuplicateEmail } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { CheckDuplicateEmailProps } from "@/types/userType";

const usePostCheckDuplicateEmail = () => {
  return useMutation(
    (CheckDuplicateEmailData: CheckDuplicateEmailProps) =>
      postCheckDuplicateEmail(CheckDuplicateEmailData),
    {
      onSuccess: () => {
        console.log("CheckDuplicateEmail Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostCheckDuplicateEmail };
