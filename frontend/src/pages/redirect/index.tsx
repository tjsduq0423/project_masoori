import { userInfoState } from "@/states/userState";
import { useRecoilState } from "recoil";

const RedirectPage = () => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const params = new URLSearchParams(location.search);
  const accessToken = params.get("accessToken");

  localStorage.setItem("accessToken", accessToken!);

  window.location.href = "/main";
  return <></>;
};

export default RedirectPage;
