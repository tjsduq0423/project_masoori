import React, { ReactNode, useState } from "react";
import styled from "styled-components";
import MenuButton from "@/assets/img/MenuButton.png";
import Modal from "@/components/modal/login";

const StyledContainer = styled.div`
  position: absolute;
  right: 0px;
  top: 0px;
  height: 8%;
  width: 10%;
  z-index: 99;
  /* background-color: black; */
`;

const TransparentContainer = styled(StyledContainer)`
  background-color: rgba(0, 0, 0, 0.5);
  height: 585%;
  width: 100%;
`;
const MenuButtonImage = styled.img`
  position: absolute;
  right: 20px;
  top: 20px;
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
        <TransparentContainer onClick={handleBackgroundClick}>
          <div>
            <MenuButtonImage src={MenuButton} onClick={openModal} />
            {isModalOpen && <Modal />}
          </div>
        </TransparentContainer>
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
