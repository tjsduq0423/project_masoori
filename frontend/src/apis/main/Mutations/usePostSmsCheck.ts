import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postSmsCheck } from "@/apis/main/mainAPI";
import { UserSmsCheck } from "@/types/verifyInfoType";

const usePostSmsCheck = () => {
  const queryClient = useQueryClient();

  return useMutation(
    (userSmsCheck: UserSmsCheck) => postSmsCheck(userSmsCheck),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(["smsCheck"]);
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostSmsCheck };
