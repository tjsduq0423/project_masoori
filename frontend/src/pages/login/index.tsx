import React, { useState } from "react";
import styled from "styled-components";
import MenuButton from "@/assets/img/MenuButton.png";
import Modal from "@/components/modal/login";

const Container = styled.div`
  background-color: rgba(64, 10, 10, 0.7);
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const MenuButtonImage = styled.img`
  position: absolute;
  right: 20px;
  top: 20px;
`;

const Login: React.FC = () => {
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
    <Container onClick={handleBackgroundClick}>
      <div>
        <MenuButtonImage src={MenuButton} onClick={openModal} />
        {isModalOpen && <Modal />}
      </div>
    </Container>
  );
};

export default Login;
