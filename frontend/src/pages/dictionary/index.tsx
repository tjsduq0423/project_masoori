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

const DictionaryPage = () => {
  const [currentPage, setCurrentPage] = useState(0);

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
    hoverable: true,
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
                <DcitBtn text="공유하기" />
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
                <ChallengeBubble {...crystalChallengeBubbleProps} />
                <ChallengeBubble {...crystalChallengeBubbleProps} />
                <ChallengeBubble {...crystalChallengeBubbleProps} />
                <ChallengeBubble {...crystalChallengeBubbleProps} />
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
    </PageContainer>
  );
};

export default DictionaryPage;
