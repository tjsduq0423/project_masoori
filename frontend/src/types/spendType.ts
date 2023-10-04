interface UserSms {
  name: string;
  phoneNumber: string;
}

interface UserSmsCheck {
  phoneNumber: string;
  code: string;
}

interface UserVerifyInfo {
  name: string;
  phoneNumber: string;
  code: string;
}

interface SpendInfo {
  card: {
    id: number;
    name: string;
    imagePath: string;
    description: string;
    createdDate: Date;
    cardType: string;
  };
  basicList: [
    {
      id: number;
      keyword: string;
      totalAmount: number;
      frequency: number;
    },
  ];
}

interface SSEInfo {
  timeout: number;
}

export type { UserSms, UserSmsCheck, UserVerifyInfo, SpendInfo, SSEInfo };
