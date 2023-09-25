interface SendPhoneSMSProps {
  phoneNumber: string;
  name: string;
}

interface CheckPhoneSMSProps {
  phoneNumber: string;
  code: string;
}

interface SighUpProps {
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

export type {
  CheckSignUpCodeProps,
  RenewPasswordProps,
  SendSignUpCodeProps,
  LoginProps,
  SendPhoneSMSProps,
  CheckPhoneSMSProps,
  SighUpProps,
};
