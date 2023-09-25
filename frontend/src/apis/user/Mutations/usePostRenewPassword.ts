import { postRenewPassword } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { RenewPasswordProps } from "@/types/userType";

const usePostRenewPassword = () => {
  return useMutation((RenewPasswordData: RenewPasswordProps) =>
    postRenewPassword(RenewPasswordData)
  );
};

export { usePostRenewPassword };
