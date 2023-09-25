import { postLogout } from "@/apis/user/userAPI";
import { useMutation } from "@tanstack/react-query";

const usePostLogout = () => {
  return useMutation(() => postLogout());
};

export { usePostLogout };
