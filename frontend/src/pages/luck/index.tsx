import React, { useEffect, useState } from "react";
import styled from "styled-components";
import CardFlip from "@/components/cardFlip";
import { useRecoilState } from "recoil";
import { useNavigate } from "react-router-dom";
import { useFortune } from "@/apis/luck/Queries/useFortune";
import { luckInfoState } from "@/states/luckState";

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
  width: 20%;
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-top: 3%;
`;

const Title = styled.div`
  color: #fdf1f1;
  font-family: "Brodies";
  font-size: 3.5vw;
  font-weight: 400;
  margin: 0px 4%;
`;

const CardSection = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 30px;
  gap: 300px;
  flex-grow: 1;
`;

const data = {
  name: "행운",
  imagePath:
    "https://images.unsplash.com/photo-1618654661521-b9b59166b17f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
  description: "이 카드를 뽑은 당신! 오늘은...",
};

const MoneyLuckPage = () => {
  const [selectedCard, setSelectedCard] = useState<number | null>(null);
  const [luckInfo, setLuckInfo] = useRecoilState(luckInfoState);
  const navigate = useNavigate();

  useEffect(() => {
    setLuckInfo(data);
  }, [setLuckInfo]);

  // const fortuneDto = useFortune();

  const handleCardClick = (index: number) => {
    if (selectedCard === null) {
      setSelectedCard(index);
      console.log("성공");

      setTimeout(() => {
        navigate("/luckcontent");
      }, 1500); // 2초 뒤에 네비게이션
    }
  };

  const isClickable = (index: number) =>
    selectedCard === null || selectedCard === index;

  const renderCardFlips = () => {
    return [0, 1, 2, 3].map((index) => (
      <CardFlip
        key={index}
        onClick={() => handleCardClick(index)}
        isClickable={isClickable(index)}
        imageSrc={luckInfo.imagePath}
      />
    ));
  };

  return (
    <Container>
      <Header>
        <Image src={headerDecorationLeft} alt="Background" />
        <Title>Select your Card</Title>
        <Image src={headerDecorationRight} alt="Background" />
      </Header>
      <CardSection>{renderCardFlips()}</CardSection>
    </Container>
  );
};

export default MoneyLuckPage;
