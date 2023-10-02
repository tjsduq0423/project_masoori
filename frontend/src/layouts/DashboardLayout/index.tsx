import React, { ReactNode, useEffect } from "react";
import styled from "styled-components";
import MenuButton from "@/assets/img/MenuButton.png";
import Modal from "@/components/modal/login";
import SignInModalBack from "@/assets/img/signCard/signInModalBack.png";
import LandingMainLogo from "@/assets/img/HeaderLogo/masooriHeaderLogo.png";
import { useLocation, useNavigate } from "react-router-dom";
import { modalOpenState } from "@/states/userState";
import { useRecoilState } from "recoil";

const StyledContainer = styled.div`
  position: absolute;
  right: 0px;
  top: 0px;
  height: 8%;
  width: 100%;
  z-index: 1;
`;

const MainLogo = styled.div`
  height: 100px;
  width: 200px;
  background-image: url(${LandingMainLogo});
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  margin-left: 15px;
`;

const TransparentContainer = styled(StyledContainer)`
  height: 585vh;
  width: 100%;
`;

const IsModalOpenBack = styled.div`
  position: absolute;
  right: 0px;
  top: 0px;
  width: 100%;
  height: 585%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
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
  z-index: 1;
`;

interface DashboardLayoutProps {
  children: ReactNode; // children을 React.ReactNode 타입으로 지정
}

const DashboardLayout: React.FC<DashboardLayoutProps> = ({ children }) => {
  const [isModalOpen, setIsModalOpen] = useRecoilState(modalOpenState);
  const location = useLocation();
  console.log(location.pathname);
  const openModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const handleBackgroundClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.target === e.currentTarget) {
      setIsModalOpen(false);
    }
  };
  const AT = localStorage.getItem("accessToken");
  const navigate = useNavigate();

  const navigatePage = () => {
    if (!AT) {
      console.log(1);
      openModal();
    } else if (AT && AT.length > 0 && location.pathname !== "/menu") {
      console.log(2);
      navigate("/menu");
    } else if (AT && AT.length > 0 && location.pathname === "/menu") {
      console.log(3);
      navigate("/main");
    }
  };

  useEffect(() => {
    if (AT && AT.length > 0 && location.pathname !== "/menu") {
      setIsModalOpen(false);
    }
  }, [AT, location.pathname, setIsModalOpen]);

  useEffect(() => {
    if (isModalOpen) {
      // 모달이 열릴 때 스크롤바 숨김
      document.body.style.overflow = "hidden";
    } else {
      // 모달이 닫힐 때 스크롤바 복원
      document.body.style.overflow = "visible";
    }

    // 컴포넌트가 언마운트될 때 스타일을 원래대로 복원
    return () => {
      document.body.style.overflow = "visible";
    };
  }, [isModalOpen]);

  return (
    <>
      {isModalOpen ? (
        <IsModalOpenBack>
          <LoginBackImg />
          <TransparentContainer onClick={handleBackgroundClick}>
            <div
              style={{
                width: "100vw",
                display: "flex",
                justifyContent: "space-between",
                alignContent: "center",
              }}
            >
              <MainLogo
                onClick={() => {
                  navigate("/main");
                }}
              />
              <MenuButtonImage src={MenuButton} onClick={navigatePage} />
              {isModalOpen && <Modal />}
            </div>
          </TransparentContainer>
        </IsModalOpenBack>
      ) : (
        <StyledContainer onClick={handleBackgroundClick}>
          <div
            style={{
              width: "100vw",
              display: "flex",
              justifyContent: "space-between",
              alignContent: "center",
            }}
          >
            <MainLogo
              onClick={() => {
                navigate("/main");
              }}
            />
            <MenuButtonImage src={MenuButton} onClick={navigatePage} />
            {isModalOpen && <Modal />}
          </div>
        </StyledContainer>
      )}
      <div>{children}</div>
    </>
  );
};

export default DashboardLayout;
