import styled from "styled-components";
import SettingFinancialGoalsCard from "@/assets/img/SettingFinancialGoals.png";
import React, { useState, useEffect } from "react";
import { settingModalOpenState } from "@/states/spendState";
import { toast } from "react-toastify";
import { useRecoilState } from "recoil";
import { usePostSetGoalAmount } from "@/apis/user/Mutations/usePostSetGoalAmount";
import { SettingMonthlyGoalProps } from "@/types/userType";

const Container = styled.div`
  position: absolute;
  left: 32.5%;
  top: 5%;
`;

const CardImg = styled.div`
  position: absolute;
  height: 65vh;
  width: 21vw;
  background-image: url(${SettingFinancialGoalsCard});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  z-index: 99;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

const Context = styled.div`
  height: 60%;
  width: 75%;
  display: flex;
  justify-content: start;
  align-items: center;
  flex-direction: column;
`;

const FormLabel = styled.div`
  display: flex;
  width: 16.5vw;
  font-size: small;
  font-weight: bold;
  margin-bottom: 2px;
  color: #5e3a66;
`;

const Amount = styled(FormLabel)`
  margin: 15px 0px;
`;

const Input = styled.input`
  width: 17vw;
  border-radius: 5px;
  border-width: 1px;
  border-color: #5e3a66;
  height: 30px;
  padding-left: 5px;
  color: black;

  &:focus {
    border-color: #5e3a66; /* 클릭했을 때 테두리 색상 변경 */
  }
`;

const ConfirmButton = styled.button`
  width: 17vw;
  border-radius: 5px;
  border-color: #5e3a66;
  border-width: 2px;
  margin-bottom: 20px;
  height: 30px;
  background-color: #5e3a66;
  color: white;
  font-weight: bold;
  font-size: 12px;
`;

interface MyComponentProps {
  monthlySpendingGoal: number;
}

const SettingFinancialGoals: React.FC<MyComponentProps> = ({
  monthlySpendingGoal,
}) => {
  const [isSettingOpen, setIsSettingOpen] = useRecoilState(
    settingModalOpenState
  );
  const [goalValue, setGoalValue] = useState<SettingMonthlyGoalProps>({
    monthlySpendingGoal: monthlySpendingGoal,
  });
  const [weeklyGoalValue, setWeeklyGoalValue] = useState<number>(
    Math.round(goalValue.monthlySpendingGoal / 4)
  );
  const [monthlyGoalValue, setMonthlyGoalValue] = useState<number>(
    Math.round(goalValue.monthlySpendingGoal / 30)
  );

  useEffect(() => {
    setWeeklyGoalValue(Math.round(goalValue.monthlySpendingGoal / 4));
    setMonthlyGoalValue(Math.round(goalValue.monthlySpendingGoal / 30));
  }, [goalValue]);

  const setGoals = usePostSetGoalAmount();

  const handleSetGoals = async () => {
    try {
      const result = await setGoals.mutateAsync(goalValue);
      console.log(result);
      if (result?.status === 200) {
        toast.info("💲 변경되었습니다 💲");
        setIsSettingOpen(false);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Container>
      <CardImg>
        <Context>
          <FormLabel>이번달 목표 ( 30일 기준 )</FormLabel>
          <Input
            value={goalValue.monthlySpendingGoal}
            onChange={(e) =>
              setGoalValue({ monthlySpendingGoal: Number(e.target.value) })
            }
          />
          <FormLabel>이번주 목표</FormLabel>
          <Amount>{weeklyGoalValue} 만원</Amount>
          <FormLabel>오늘의 목표</FormLabel>
          <Amount>{monthlyGoalValue} 만원</Amount>
          <ConfirmButton
            onClick={() => {
              handleSetGoals();
            }}
          >
            Confirm
          </ConfirmButton>
        </Context>
      </CardImg>
    </Container>
  );
};

export default SettingFinancialGoals;
