import React, { useState } from "react";
import styled from "styled-components";
import background from "@/assets/img/background/silkBackground.jpg";
import TarotCard from "@/components/tarotCard";
import tarotCardFront from "@/assets/img/tarotCard/tarotCardFront.png";
import tarotCardBack from "@/assets/img/tarotCard/tarotCardBack.png";
import HashTag from "@/components/hashtag";
import TextBubble from "@/components/textBubble";
import { StyledTextBubbleProps } from "@/types/luckType";

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

const ContentContainer = styled.div`
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  flex-direction: row; /* 수평 정렬로 변경 */
  height: 100%;
`;

const CardContainer = styled.div`
  padding: 70px 55px;
`;

const Title = styled.div`
  font-size: 60px;
  color: white;
  font-family: "Brodies";
`;

const TitleContainer = styled.div`
  padding: 0px 0px 0px 55px;
  text-align: left;
`;

const HashtagContainer = styled.div`
  display: flex;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 25px 0px 25px 0px;
`;

const TextBubbleContainer = styled.div`
  margin-bottom: 10px; /* 각 텍스트 버블 사이의 간격 조정 */
`;

const SpendPage: React.FC = () => {
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

  const contentTextBubbleProps: StyledTextBubbleProps = {
    text: `오늘은 지출이 많을 것 같은걸? 약속이 있다면 각오해야 할거야.
    어머! 표정 좀 풀렴. 내가 네 돈을 쓰는 것도 아니잖니?
    무언가 구매하고 싶다면 참는 게 좋겠어. 별의 흐름이 속삭이길, 오늘은 욕심없이 지내는 게 좋겠다고 하는구나.
    요정들의 이야기도 들어볼까?
    음...... 요정들은 돈만 걱정하는 게 아닌 것 같은데?
    네 눈에는 보이지 않겠지만, 나쁜 요정들도 존재한단다. 그들이 네게 장난칠 궁리를 하는 걸 이 아이들이 들은 모양이야. 오늘은 일찍 집에 가는 게 좋겠어.
    아무래도 수정구가 네게 행운을 가져다 줄 색상을 보여주고 싶은 것 같네.
    아까부터 은하수같은 빛이 나오는 게 보이지?
    손을 대보겠니?`,
    width: "650px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "40px",
    borderRadius: "20px",
    hoverable: false,
  };

  const crystalTextBubbleProps: StyledTextBubbleProps = {
    text: `🔮 수정구에 손을 올린다 🔮`,
    width: "650px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "20px",
    borderRadius: "20px",
    hoverable: true,
  };

  const shareTextBubbleProps: StyledTextBubbleProps = {
    text: `🃏 나의 카드를 다른 사람들에게 공유한다 🃏`,
    width: "650px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "20px",
    borderRadius: "20px",
    hoverable: true,
  };

  return (
    <PageContainer>
      <ContentContainer>
        <CardContainer>
          <TarotCard
            width="300px"
            height="402px"
            cardWidth="100%"
            cardSrc={tarotCardFront}
            imageSrc={tarotCardBack}
            bottomImageWidth="100%"
            text="Special Card"
            fontsize="20px"
          ></TarotCard>
        </CardContainer>
        <TitleContainer>
          <Title>This Week&apos;s Tarot Card</Title>
          <HashtagContainer>
            <HashTag text="Food"></HashTag>
            <HashTag text="Food"></HashTag>
            <HashTag text="Food"></HashTag>
            <HashTag text="Food"></HashTag>
            <HashTag text="Food"></HashTag>
          </HashtagContainer>
          <ContentWrapper>
            <TextBubbleContainer>
              <TextBubble {...titleTextBubbleProps} />
            </TextBubbleContainer>
            <TextBubbleContainer>
              <TextBubble {...contentTextBubbleProps} />
            </TextBubbleContainer>
            <TextBubbleContainer>
              <TextBubble {...crystalTextBubbleProps} />
            </TextBubbleContainer>
            <TextBubbleContainer>
              <TextBubble {...shareTextBubbleProps} />
            </TextBubbleContainer>
          </ContentWrapper>
        </TitleContainer>
      </ContentContainer>
    </PageContainer>
  );
};

export default SpendPage;
