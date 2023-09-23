import React, { useState } from "react";
import styled from "styled-components";
import background from "@/assets/img/background/silkBackground.jpg";
import TarotCard from "@/components/tarotCard";
import tarotCardFront from "@/assets/img/tarotCard/tarotCardFront.png";
import tarotCardBack from "@/assets/img/tarotCard/tarotCardBack.png";
import HashTag from "@/components/hashtag";
import TextBubble from "@/components/textBubble";
import { StyledTextBubbleProps } from "@/types/luckType";
import GhostModal from "@/components/ghostModal";
import AlertModal from "@/components/alertModal";
import puzzle from "@/assets/img/puzzle.png";

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

const ContentContainer = styled.div`
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  flex-direction: row; /* 수평 정렬로 변경 */
  height: 100%;
  width: 100%;
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

const ModalContainer = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  display: ${(props) => (props.isOpen ? "block" : "none")};
  z-index: 3;
`;

const PuzzleModalContainer = styled.div<{ isPuzzleOpen: boolean }>`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  display: ${(props) => (props.isPuzzleOpen ? "block" : "none")};
  z-index: 3;
`;

const SpendPage: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 열림 여부 상태
  const [isPuzzleModalOpen, setIsPuzzleModalOpen] = useState(false);

  // 모달 열기 함수
  const toggleModal: () => void = () => {
    setIsModalOpen(!isModalOpen);
  };

  // 모달 열기 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const openPuzzleModal = () => {
    setIsPuzzleModalOpen(true);
  };

  const closePuzzleModal = () => {
    setIsPuzzleModalOpen(false);
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

  const contentTextBubbleProps: StyledTextBubbleProps = {
    text: `별들이 제법 많이 떴는걸? 어디 보자......
    마법 같은 변화가 일어났구나! 🧙‍♀️ 저번주 대비 전체적인 소비금액이 줄어들었지만, 음식에 대한 소비를 많이 했구나
    이러한 변화가 발생한 이유를 알아보고 더 나은 재정 상태를 유지할 수 있는 방법을 고민해보자.
    네 미래의 지출도 알고 싶다고? 어렵지 않지. 손을 줘보겠니?
    아니면, 네 카드를 지금 바로 다른 인간들에게 보여줄래?`,
    width: "650px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "40px",
    borderRadius: "20px",
    hoverable: false,
  };

  const crystalTextBubbleProps: StyledTextBubbleProps = {
    text: `🔮 마녀에게 손을 내민다 🔮`,
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
    <div>
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
              <TextBubbleContainer onClick={openPuzzleModal}>
                <TextBubble {...crystalTextBubbleProps} />
              </TextBubbleContainer>
              <TextBubbleContainer onClick={openModal}>
                <TextBubble {...shareTextBubbleProps} />
              </TextBubbleContainer>
            </ContentWrapper>
          </TitleContainer>
          <ModalContainer isOpen={isModalOpen}>
            <GhostModal zIndex={"3"} toggleModal={toggleModal} />
          </ModalContainer>
          <PuzzleModalContainer isPuzzleOpen={isPuzzleModalOpen}>
            <AlertModal
              width="600px"
              topText="퍼즐을 찾았어요"
              middleText="우리 함께 살펴볼까요?"
              bottomText="내 진행상황 보러가기"
              imageUrl={puzzle} // 이미지 경로
              topTextColor="#5E3A66"
              middleTextColor="#5E3A66"
              bottomTextColor="white"
              upperSectionBackground="#EAE2ED"
              lowerSectionBackground="#5E3A66"
              topTextFontSize="28px"
              middleTextFontSize="14px"
              bottomTextFontSize="20px"
              topTextPaddingTopBottom="20px"
              middleTextPaddingTopBottom="6px"
              topTextFontWeight="bold"
              middleTextFontWeight="medium"
              bottomTextFontWeight="medium"
              zIndex={"3"}
            />
          </PuzzleModalContainer>
        </ContentContainer>
        <Backdrop isOpen={isModalOpen} onClick={closeModal} />
        <Backdrop isOpen={isPuzzleModalOpen} onClick={closePuzzleModal} />
      </PageContainer>
    </div>
  );
};

export default SpendPage;
