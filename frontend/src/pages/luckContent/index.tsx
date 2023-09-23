import { useState } from "react";
import styled from "styled-components";
import TextBubble from "@/components/textBubble";
import { StyledTextBubbleProps } from "@/types/luckType";
import background from "@/assets/img/background/capetBackground.jpg";
import headerDecorationLeft from "@/assets/img/headerDecorationLeft.png";
import headerDecorationRight from "@/assets/img/headerDecorationRight.png";
import cardFront from "@/assets/img/cardFront.png";

const Container = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(${background});
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 4% 0;
`;

const Title = styled.div`
  color: #fdf1f1;
  font-family: "Brodies";
  font-size: 3.5vw;
  font-weight: 400;
  margin: 0px 4%;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 50px 40px;
`;

const TextBubbleContainer = styled.div`
  margin-bottom: 20px; /* 각 텍스트 버블 사이의 간격 조정 */
`;

const Image = styled.img`
  width: 20%; /* 이미지의 너비를 화면 너비의 10%로 조정 */
`;

const CardImage = styled.img`
  width: 350px;
  margin-right: 50px;
  /* 수직 가운데 정렬을 위해 margin을 추가 */
`;

const LuckContentPage = () => {
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
    <Container>
      <Header>
        <Image src={headerDecorationLeft} alt="Background" />
        <Title>The Witch’s Answer</Title>
        <Image src={headerDecorationRight} alt="Background" />
      </Header>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <CardImage src={cardFront} alt="Background" />
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
        </ContentWrapper>
      </div>
    </Container>
  );
};

export default LuckContentPage;
