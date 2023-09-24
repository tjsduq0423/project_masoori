import React, { ReactNode, useState } from "react";
import styled from "styled-components";
import MenuButton from "@/assets/img/MenuButton.png";
import Modal from "@/components/modal/login";
import SignInModalBack from "@/assets/img/signCard/signInModalBack.png";

const StyledContainer = styled.div`
  position: absolute;
  right: 0px;
  top: 0px;
  height: 8%;
  width: 10%;
  z-index: 99;
`;

const TransparentContainer = styled(StyledContainer)`
  height: 585%;
  width: 100%;
`;

const IsModalOpenBack = styled.div`
  position: absolute;
  right: 0px;
  top: 0px;
  width: 100%;
  height: 585%;
  background-color: rgba(0, 0, 0, 0.5);
`;

const MenuButtonImage = styled.img`
  position: absolute;
  right: 20px;
  top: 20px;
`;

const LoginBackImg = styled.div`
  position: absolute;
  left: 9.75%;
  top: 4%;
  height: 70vh;
  width: 80vw;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-image: url(${SignInModalBack});
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
`;

interface DashboardLayoutProps {
  children: ReactNode; // children을 React.ReactNode 타입으로 지정
}

const DashboardLayout: React.FC<DashboardLayoutProps> = ({ children }) => {
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleBackgroundClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.target === e.currentTarget) {
      closeModal();
    }
  };

  return (
    <>
      {isModalOpen ? (
        <IsModalOpenBack>
          <LoginBackImg />
          <TransparentContainer onClick={handleBackgroundClick}>
            <div>
              <MenuButtonImage src={MenuButton} onClick={openModal} />
              {isModalOpen && <Modal />}
            </div>
          </TransparentContainer>
        </IsModalOpenBack>
      ) : (
        <StyledContainer onClick={handleBackgroundClick}>
          <div>
            <MenuButtonImage src={MenuButton} onClick={openModal} />
            {isModalOpen && <Modal />}
          </div>
        </StyledContainer>
      )}
      <div>{children}</div>
    </>
  );
};

export default DashboardLayout;
