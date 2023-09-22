// import { PATH } from "@/constants/path"; 리다이렉트시킬 페이지
import { userInfoState } from "@/states/userState";
import { produce } from "immer";
import { useRecoilState } from "recoil";
// import jwt from "jwt-decode";
import { useEffect } from "react";

interface JwtProps {
  auth: string;
  exp: number;
  sub: string;
}

const RedirectPage = () => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const params = new URLSearchParams(location.search);
  const accessToken = params.get("code");
  // const userId = jwt<JwtProps>(accessToken!).sub;
  // const userAuth = jwt<JwtProps>(accessToken!).auth;
  localStorage.setItem("accessToken", accessToken!);

  setUserInfo(
    produce((draft) => {
      // draft.userId = parseInt(userId);
      // draft.auth = userAuth;
    })
  );

  // window.location.href = PATH.SIGNIN; 리다이렉트시킬 페이지
  return <></>;
  // return <div>as;ldfjas;ldf</div>;
};

export default RedirectPage;
