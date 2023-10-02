const RedirectPage = () => {
  const params = new URLSearchParams(location.search);
  const accessToken = params.get("accessToken");

  localStorage.setItem("accessToken", accessToken!);
  if (accessToken && accessToken.length > 0) {
    window.location.href = "/main";
  }
  return <></>;
};

export default RedirectPage;
