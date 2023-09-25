import React, { useState } from "react";
import Book from "@/components/book";
import styled from "styled-components";
import PokemonCard from "@/components/Pokemon";
import TarotCard from "@/components/tarotCard";
import DcitBtn from "@/components/dictBtn";
import card from "@/assets/img/cardFront.png";
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

const DictionaryPage = () => {
  const [currentPage, setCurrentPage] = useState(0);

  const initialTarotCards = Array.from({ length: 30 }, (_, index) => (
    <TarotCard
      key={index}
      width="80%"
      height="300px"
      cardWidth="100%"
      cardSrc={card}
      imageSrc="path/to/your/image.jpg"
      bottomImageWidth="80%"
      text=""
      fontsize="16px"
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
                <div key={index}>{card}</div>
              ))}
            </div>
          </div>
        )}
      </ContentSection>
    </PageContainer>
  );
};

export default DictionaryPage;
