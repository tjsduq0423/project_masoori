import axios, { Axios, AxiosError, AxiosResponse } from "axios";

const instance: Axios = axios.create({
  baseURL: `${process.env.REACT_APP_SERVER_URL}/api`,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${localStorage.getItem("accessToken") || undefined}`,
  },
});

instance.interceptors.response.use(
  (response: AxiosResponse) => {
    // 헤더를 뜯어서 accessToken을 저장한다
    const accessToken = response.headers["x-access-token"];
    if (accessToken !== null && accessToken !== undefined) {
      localStorage.setItem("accessToken", accessToken);
    }

    return response;
  },
  (error: AxiosError) => {
    if (error.response?.status === 401) {
      // 로그아웃 시키고 로그인 PAGE로 리다이렉트
      localStorage.clear();
      window.location.href = "/main";
    }
    return Promise.reject(error);
  }
);

export { instance };
