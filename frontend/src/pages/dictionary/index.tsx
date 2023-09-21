import React, { useState } from "react";
import Book from "@/components/book";
import styled from "styled-components";
import PokemonCard from "@/components/Pokemon";
import TextBubble from "@/components/textBubble";
import { StyledTextBubbleProps } from "@/types/luckType";
import TarotCard from "@/components/tarotCard";
import DcitBtn from "@/components/dictBtn";

import card from "@/assets/img/cardFront.png";
import background from "@/assets/img/background/silkBackground.jpg";

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
  font-family: "Pyeongchangpeace";
  color: #d39090;
  font-size: 36px;
`;

const BasicHeader = styled.div`
  display: "flex";
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
  const [tarotCards, setTarotCards] = useState<JSX.Element[]>([]); // Specify the type as JSX.Element[]

  // Function to add a Tarot card
  const addTarotCard = () => {
    const newCard = (
      <TarotCard
        key={tarotCards.length} // Use the length of the array as the key
        width="120px"
        height="200px"
        cardWidth="100%"
        cardSrc={card}
        imageSrc="path/to/your/image.jpg"
        bottomImageWidth="80%"
        text=""
        fontsize="16px"
      />
    );
    setTarotCards([...tarotCards, newCard]);
  };

  // Function to handle page change
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const crystalTextBubbleProps: StyledTextBubbleProps = {
    text: `🔮 수정구에 손을 올린다 🔮`,
    width: "300px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "20px",
    borderRadius: "20px",
    hoverable: false,
  };

  return (
    <PageContainer>
      <BookSection>
        <BookTitle>2023.09</BookTitle>
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
                gridTemplateColumns: "repeat(5, 1fr)", // Adjust the number of columns as needed
                gap: "20px", // Adjust the gap between cards as needed
              }}
            >
              {tarotCards.map((card, index) => (
                <div
                  key={index}
                  style={{ marginRight: "10px", marginBottom: "20px" }}
                >
                  {card}
                </div>
              ))}
            </div>
          </div>
        )}
        {currentPage === 2 && (
          <div>
            <SpecialHeader>
              <SpecialText>2023.09</SpecialText>
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
                <TextBubble {...crystalTextBubbleProps} />
                <TextBubble {...crystalTextBubbleProps} />
                <TextBubble {...crystalTextBubbleProps} />
                <TextBubble {...crystalTextBubbleProps} />
              </div>
            </div>
          </div>
        )}
        {currentPage === 4 && (
          <div>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(5, 1fr)", // Adjust the number of columns as needed
                gap: "20px", // Adjust the gap between cards as needed
              }}
            >
              {tarotCards.map((card, index) => (
                <div
                  key={index}
                  style={{ marginRight: "10px", marginBottom: "20px" }}
                >
                  {card}
                </div>
              ))}
            </div>
            <button onClick={addTarotCard}>Add Tarot Card</button>
          </div>
        )}
      </ContentSection>
    </PageContainer>
  );
};

export default DictionaryPage;
