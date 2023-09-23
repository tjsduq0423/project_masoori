import styled from "styled-components";
import Lottie from "lottie-react";
import loadingWitch from "@/assets/img/gif/witch.json";

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

const LoadingPage = () => {
  return (
    <Container>
      <Lottie animationData={loadingWitch} />
    </Container>
  );
};

export default LoadingPage;
