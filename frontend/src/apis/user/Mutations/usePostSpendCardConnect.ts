import { postSpendCardConnect } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostSpendCardConnect = () => {
  return useMutation(() => postSpendCardConnect());
};

export { usePostSpendCardConnect };
