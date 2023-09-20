import React, { useState } from "react";
import styled from "styled-components";
import CardFlip from "@/components/CardFlip";

import background from "@/assets/img/background/capetBackground.jpg";
import headerDecorationLeft from "@/assets/img/headerDecorationLeft.png";
import headerDecorationRight from "@/assets/img/headerDecorationRight.png";

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
`;

const Image = styled.img`
  width: 20%; /* 이미지의 너비를 화면 너비의 10%로 조정 */
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 4% 0; /* 헤더 상단과 하단의 패딩을 10%로 조정 */
`;

const Title = styled.div`
  color: #fdf1f1;
  font-family: "Brodies";
  font-size: 3.5vw; /* 글자 크기를 화면 너비의 4%로 조정 */
  font-weight: 400;
  margin: 0px 4%; /* 위 아래 여백을 조정 */
`;

const CardSection = styled.div`
  display: flex;
  align-items: center;
  justify-content: center; /* 중앙 정렬 */
  gap: 300px; /* 카드 사이의 간격 설정 */
  margin-right: 200px;
  flex-grow: 1; /* 나머지 공간을 모두 차지하도록 설정 */
`;

const MoneyLuckPage: React.FC = () => {
  const [selectedCard, setSelectedCard] = useState<number | null>(null);

  const handleCardClick = (index: number) => {
    if (selectedCard === null) {
      // 클릭한 카드를 선택하고 다른 카드의 클릭 가능 상태를 비활성화
      setSelectedCard(index);
      console.log("성공");
    }
  };

  const isClickable = (index: number) => {
    return selectedCard === null || selectedCard === index;
  };

  return (
    <Container>
      <Header>
        <Image src={headerDecorationLeft} alt="Background" />
        <Title>Select your Card</Title>
        <Image src={headerDecorationRight} alt="Background" />
      </Header>
      <CardSection>
        <CardFlip
          onClick={() => handleCardClick(0)}
          isClickable={isClickable(0)}
        />
        <CardFlip
          onClick={() => handleCardClick(1)}
          isClickable={isClickable(1)}
        />
        <CardFlip
          onClick={() => handleCardClick(2)}
          isClickable={isClickable(2)}
        />
        <CardFlip
          onClick={() => handleCardClick(3)}
          isClickable={isClickable(3)}
        />
      </CardSection>
    </Container>
  );
};

export default MoneyLuckPage;
