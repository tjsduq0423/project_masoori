import { postCheckPhoneSMS } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { CheckPhoneSMSProps } from "@/types/userType";

const usePostCheckPhoneSMS = () => {
  return useMutation(
    (CheckPhoneSMSData: CheckPhoneSMSProps) =>
      postCheckPhoneSMS(CheckPhoneSMSData),
    {
      onSuccess: () => {
        console.log("CheckPhoneSMS Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostCheckPhoneSMS };
