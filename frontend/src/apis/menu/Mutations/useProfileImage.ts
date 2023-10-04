import { useMutation, useQueryClient } from "@tanstack/react-query";
import { postProfileImage } from "@/apis/menu/menuAPI";

const useProfileImage = () => {
  const queryClient = useQueryClient();

  return useMutation((id: number) => postProfileImage(id), {
    onSuccess: () => {
      queryClient.invalidateQueries(["profileImage"]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useProfileImage };
