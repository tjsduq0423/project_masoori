import styled from "styled-components";
import Lottie from "lottie-react";
import errorGif from "@/assets/img/gif/error.json";

const Container = styled.div`
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #202020;
`;

const ErrorPage = () => {
  return (
    <Container>
      <Lottie animationData={errorGif} />
    </Container>
  );
};

export default ErrorPage;
