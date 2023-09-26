import { useState } from "react";
import styled from "styled-components";
import TextBubble from "@/components/textBubble";
import { StyledTextBubbleProps } from "@/types/luckType";
import { useRecoilValue } from "recoil";
import { luckInfoState } from "@/states/luckState";
import { useNavigate } from "react-router-dom";
import TarotCard from "@/components/tarotCard";

import background from "@/assets/img/background/capetBackground.jpg";
import headerDecorationLeft from "@/assets/img/headerDecorationLeft.png";
import headerDecorationRight from "@/assets/img/headerDecorationRight.png";
import cardFront from "@/assets/img/tarotCard/tarotCardFront.png";

const PageContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
  background-size: cover;
  background-attachment: scroll;
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

const ContentContainer = styled.div`
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  flex-direction: row; /* 수평 정렬로 변경 */
  height: 75%;
  width: 100%;
`;

const CardContainer = styled.div`
  padding: 70px 55px;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 25px 0px 25px 0px;
`;

const TextBubbleContainer = styled.div`
  margin-bottom: 20px; /* 각 텍스트 버블 사이의 간격 조정 */
`;

const Image = styled.img`
  width: 20%; /* 이미지의 너비를 화면 너비의 10%로 조정 */
`;

const TitleContainer = styled.div`
  padding: 0px 0px 0px 55px;
  text-align: left;
`;

const LuckContentPage = () => {
  const luckInfo = useRecoilValue(luckInfoState);
  const navigate = useNavigate();

  const goCrystal = () => {
    navigate("/crystal");
  };

  const titleTextBubbleProps: StyledTextBubbleProps = {
    text: "🌟 마녀 🌟",
    width: "136PX",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "28px",
    paddingTopBottom: "15px",
    borderRadius: "10px",
    hoverable: false,
  };

  // const formattedDescription = luckInfo.description
  //   .split("\n")
  //   .map((line, index) => (
  //     <React.Fragment key={index}>
  //       {line}
  //       <br />
  //     </React.Fragment>
  //   ));

  const contentTextBubbleProps: StyledTextBubbleProps = {
    text: `${luckInfo.description.replace(/\n/g, "\n")}`,
    width: "588PX",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "40px",
    borderRadius: "20px",
    hoverable: false,
  };

  const crystalTextBubbleProps: StyledTextBubbleProps = {
    text: `🔮 수정구에 손을 올린다 🔮`,
    width: "588PX",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "20px",
    borderRadius: "20px",
    hoverable: true,
  };

  return (
    <PageContainer>
      <Header>
        <Image src={headerDecorationLeft} alt="Background" />
        <Title>The Witch’s Answer</Title>
        <Image src={headerDecorationRight} alt="Background" />
      </Header>
      <ContentContainer>
        <CardContainer>
          <TarotCard
            width="300px"
            height="402px"
            cardWidth="100%"
            cardSrc={cardFront}
            imageSrc={luckInfo.imagePath}
            bottomImageWidth="100%"
            text={luckInfo.name}
            fontsize="20px"
            bottom="1rem"
          ></TarotCard>
        </CardContainer>
        <TitleContainer>
          <ContentWrapper>
            <TextBubbleContainer>
              <TextBubble {...titleTextBubbleProps} />
            </TextBubbleContainer>
            <TextBubbleContainer>
              <TextBubble {...contentTextBubbleProps} />
            </TextBubbleContainer>
            <TextBubbleContainer>
              <TextBubble {...crystalTextBubbleProps} onClick={goCrystal} />
            </TextBubbleContainer>
          </ContentWrapper>
        </TitleContainer>
      </ContentContainer>
    </PageContainer>
  );
};

export default LuckContentPage;
