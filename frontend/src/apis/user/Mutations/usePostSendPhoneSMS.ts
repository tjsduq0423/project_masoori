import { postSendPhoneSMS } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";
import { SendPhoneSMSProps } from "@/types/userType";

const usePostSendPhoneSMS = () => {
  return useMutation((SendPhoneSMSData: SendPhoneSMSProps) =>
    postSendPhoneSMS(SendPhoneSMSData)
  );
};

export { usePostSendPhoneSMS };
