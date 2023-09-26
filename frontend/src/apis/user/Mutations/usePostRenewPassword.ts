import { postRenewPassword } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { RenewPasswordProps } from "@/types/userType";

const usePostRenewPassword = () => {
  return useMutation(
    (RenewPasswordData: RenewPasswordProps) =>
      postRenewPassword(RenewPasswordData),
    {
      onSuccess: () => {
        console.log("RenewPassword Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostRenewPassword };
