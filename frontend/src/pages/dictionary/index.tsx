import React, { useState } from "react";
import Book from "@/components/book";
import styled from "styled-components";
import PokemonCard from "@/components/Pokemon";
import TarotCard from "@/components/tarotCard";
import DcitBtn from "@/components/dictBtn";
import frontcard from "@/assets/img/tarotCard/tarotCardFront.png";
import background from "@/assets/img/background/silkBackground.jpg";
import ChallengeBubble from "@/components/challengeBubble";
import { StyledChallengeBubbleProps } from "@/types/challengeType";
import ChallegeSuccess from "@/assets/img/challengeBubble/challengeSuccess.png";
import ShareModal from "@/components/shareModal";

const PageContainer = styled.div`
  position: fixed;
  display: flex;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-image: url(${background});
  background-size: cover;
`;

const ModalContainer = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 3;
`;

const Backdrop = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8); /* Semi-transparent black background */
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 1;
`;

const BookSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 50%;
  height: 100%;
`;

const BookTitle = styled.div`
  font-family: "Pyeongchangpeace";
  color: #fdf1f1;
  font-size: 60px;
  padding: 42px;
`;

const ContentSection = styled.div`
  width: 50%;
  height: 100%;
  margin-top: 5%;
  overflow: auto;
`;

const SpecialHeader = styled.div`
  width: 100%;
  text-align: left;
  margin-left: 20px;
`;

const SpecialText = styled.div`
  display: flex;
  align-items: center;
  font-family: "Pyeongchangpeace";
  text-align: left;
  color: #d39090;
  font-size: 36px;
  margin-bottom: 10px;
`;

const BasicHeader = styled.div`
  width: 100%;
  text-align: left;
`;

const BasicText = styled.div`
  display: flex;
  align-items: center;
  font-family: "Pyeongchangpeace";
  text-align: left;
  color: #d39090;
  font-size: 24px;
  margin-bottom: 10px;
`;

const data = [
  {
    name: "행운",
    imagePath:
      "https://images.unsplash.com/photo-1500258593672-b080c40f4b02?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
    summary: "생각지도 못했던 곳에 갑자기 돈을 쓸 수 있어요.",
    description: "이 카드를 뽑은 당신! 오늘은...",
  },
  {
    name: "콜라",
    imagePath:
      "https://images.unsplash.com/photo-1500258593672-b080c40f4b02?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
    summary: "생각지도 못했던 곳에 갑자기 돈을 쓸 수 있어요.",
    description: "이 카드를 뽑은 당신! 오늘은...",
  },
];

const challengeCard = {
  card: {
    id: 1,
    name: "카드 이름",
    imagePath: "http://j9b308.p.ssafy.io/abc.jpg",
    description: "이 카드는...",
    createdDate: "2023-09-27T02:24:02.289Z",
    cardType: "BASIC, SPECIAL",
  },
  challengeList: [
    {
      id: 1,
      isSuccess: true,
      name: "커피 그만 먹어",
      achievementCondition: "커피 2번 줄이기",
      startTime: "2023-09-27T02:24:02.289Z",
      endTime: "2023-09-27T02:24:02.289Z",
    },
    {
      id: 2,
      isSuccess: true,
      name: "과자 멈춰",
      achievementCondition: "과자 2번 줄이기",
      startTime: "2023-09-27T02:24:02.289Z",
      endTime: "2023-09-27T02:24:02.289Z",
    },
    {
      id: 3,
      isSuccess: false,
      name: "저축은 해야지",
      achievementCondition: "저축 2번 하기",
      startTime: "2023-09-27T02:24:02.289Z",
      endTime: "2023-09-27T02:24:02.289Z",
    },

    {
      id: 4,
      isSuccess: false,
      name: "다이어트 맨",
      achievementCondition: "외식 2번 줄이기",
      startTime: "2023-09-27T02:24:02.289Z",
      endTime: "2023-09-27T02:24:02.289Z",
    },
  ],
};

const DictionaryPage = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);

  // 모달 열기 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const initialTarotCards = data.map((item, index) => (
    <TarotCard
      key={index}
      width="140px"
      height="100%"
      cardWidth="100%"
      cardSrc={frontcard}
      imageSrc={item.imagePath}
      bottomImageWidth="100%"
      text={item.name}
      fontsize="0.8rem"
    />
  ));

  const [tarotCards, setTarotCards] =
    useState<JSX.Element[]>(initialTarotCards);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const crystalChallengeBubbleProps: StyledChallengeBubbleProps = {
    text: `사실 말도 안되는 챌린지죠 그치만 어쩌겠습니까 해야지`,
    width: "340px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "30px",
    borderRadius: "20px",
    imgLink: ChallegeSuccess,
    titleText: "소비금액 5만원 넘지 않기",
  };

  return (
    <PageContainer>
      <BookSection>
        <BookTitle>Collection Book</BookTitle>
        <Book onPageChange={handlePageChange} />
      </BookSection>
      <ContentSection>
        {currentPage === 0 && (
          <div>
            <BasicHeader>
              <BasicText>
                2023.09 <DcitBtn text="카드추천" />
              </BasicText>
            </BasicHeader>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(5, 1fr)",
                marginTop: "2rem",
              }}
            >
              {tarotCards.map((card, index) => (
                <div key={index}>{card}</div>
              ))}
            </div>
          </div>
        )}
        {currentPage === 2 && (
          <div>
            <SpecialHeader>
              <SpecialText>
                2023.09 <DcitBtn text="카드변경" />
                <DcitBtn onClick={openModal} text="공유하기" />
              </SpecialText>
            </SpecialHeader>
            <div style={{ display: "flex" }}>
              <PokemonCard />
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-around",
                }}
              >
                {challengeCard.challengeList.map((challenge, index) => (
                  <ChallengeBubble
                    key={index}
                    text={challenge.achievementCondition} // Use achievementCondition as text
                    titleText={challenge.name} // Use name as titleText
                    width={crystalChallengeBubbleProps.width}
                    background={
                      challenge.isSuccess
                        ? "#7B263B"
                        : crystalChallengeBubbleProps.background
                    }
                    opacity={
                      challenge.isSuccess
                        ? "1"
                        : crystalChallengeBubbleProps.opacity
                    }
                    paddingLeftRight={
                      crystalChallengeBubbleProps.paddingLeftRight
                    }
                    paddingTopBottom={
                      crystalChallengeBubbleProps.paddingTopBottom
                    }
                    borderRadius={crystalChallengeBubbleProps.borderRadius}
                    imgLink={crystalChallengeBubbleProps.imgLink}
                  />
                ))}
              </div>
            </div>
          </div>
        )}
        {currentPage === 4 && (
          <div style={{ marginBottom: "100px" }}>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(4, 1fr)", // Adjust the number of columns as needed
                marginTop: "30px",
              }}
            >
              {tarotCards.map((card, index) => (
                <div key={index} style={{ marginBottom: "20px" }}>
                  {card}
                </div>
              ))}
            </div>
          </div>
        )}
      </ContentSection>
      <ModalContainer isOpen={isModalOpen}>
        <ShareModal />
      </ModalContainer>
      <Backdrop isOpen={isModalOpen} onClick={closeModal} />
    </PageContainer>
  );
};

export default DictionaryPage;
