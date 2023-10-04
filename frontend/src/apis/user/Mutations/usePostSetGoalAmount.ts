import { postSetGoalAmount } from "@/apis/user/userAPI";
import { SettingMonthlyGoalProps } from "@/types/userType";
import { useMutation } from "@tanstack/react-query";

const usePostSetGoalAmount = () => {
  return useMutation(
    (Amount: SettingMonthlyGoalProps) => postSetGoalAmount(Amount),
    {
      onSuccess: () => {
        console.log("postSetGoalAmount Success");
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { usePostSetGoalAmount };
