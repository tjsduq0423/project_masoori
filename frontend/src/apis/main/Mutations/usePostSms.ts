import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postSms } from "@/apis/main/mainAPI";
import { UserSms } from "@/types/spendType";

const usePostSms = () => {
  const queryClient = useQueryClient();

  return useMutation((userSms: UserSms) => postSms(userSms), {
    onSuccess: () => {
      queryClient.invalidateQueries(["sms"]);
    },
    onError: (err: Error) => {
      console.log("Error in usePostSms:", err);
    },
  });
};

export { usePostSms };
