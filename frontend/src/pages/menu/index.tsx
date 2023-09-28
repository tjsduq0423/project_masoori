import React, { useState } from "react";
import ProfilePage from "@/pages/profile";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import menuTitle from "../../assets/img/menuTitle.png";
import { usePostLogout } from "@/apis/user/Mutations/usePostLogout";

const PageContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #a37c9b;
  background-size: cover;
  background-attachment: scroll;
`;

const Backdrop = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7); /* Semi-transparent black background */
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 1;
`;

// Create a modal container
const ModalContainer = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 2;
`;

const MenuTitle = styled.div`
  background: url(${menuTitle}) no-repeat;
  background-size: contain;
  margin-top: 1.5rem;
  height: 300px;
  width: 550px;
`;

const MenuItem = styled.div`
  /* Style for menu items */
  padding: 10px;
  font-size: 24px;
  cursor: pointer;
  transition: background-color 0.3s ease; /* Smooth transition for background color */
  color: #fff;

  &:hover {
    background-color: #95648b; /* Change the background color on hover */
  }
`;

const MenuPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const goMain = () => {
    navigate("/main");
  };

  const goFaq = () => {
    navigate("/faq");
  };

  const Logout = () => {
    localStorage.removeItem("accessToken");
    usePostLogout();
    navigate("/main");
  };

  return (
    <PageContainer>
      <div style={{ width: "100%" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: "40px",
          }}
        >
          <MenuTitle></MenuTitle>
        </div>
        <MenuItem onClick={goMain}>MAIN</MenuItem>
        <MenuItem onClick={Logout}>LOG OUT</MenuItem>
        <MenuItem onClick={openModal}>MY PAGE</MenuItem>
        <MenuItem onClick={goFaq}>FAQ</MenuItem>
        <ModalContainer isOpen={isModalOpen}>
          <ProfilePage />
        </ModalContainer>
        <Backdrop isOpen={isModalOpen} onClick={closeModal} />
      </div>
    </PageContainer>
  );
};

export default MenuPage;
