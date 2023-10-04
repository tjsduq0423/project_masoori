interface SendPhoneSMSProps {
  phoneNumber: string;
  name: string;
}

interface CheckPhoneSMSProps {
  phoneNumber: string;
  code: string;
}

interface SignUpProps {
  email: string;
  password: string;
}

interface LoginProps {
  email: string;
  password: string;
}

interface SendSignUpCodeProps {
  email: string;
}

interface RenewPasswordProps {
  email: string;
}

interface CheckSignUpCodeProps {
  email: string;
  code: string;
}

interface CheckDuplicateEmailProps {
  email: string;
}

interface SettingMonthlyGoalProps {
  monthlySpendingGoal: number;
}

export type {
  SettingMonthlyGoalProps,
  CheckDuplicateEmailProps,
  CheckSignUpCodeProps,
  RenewPasswordProps,
  SendSignUpCodeProps,
  LoginProps,
  SendPhoneSMSProps,
  CheckPhoneSMSProps,
  SignUpProps,
};
