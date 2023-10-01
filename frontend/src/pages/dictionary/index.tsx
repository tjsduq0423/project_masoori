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

import cardBack from "@/assets/img/tarotCard/tarotCardBack.png";

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
    id: 1,
    name: "THE MAGICIAN",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251515.png",
    summary: "일개미처럼 열심히 일한다면 보람찰 거예요.",
    description:
      "오늘은 재물 운이 조금씩 움직이기 시작하는 날이에요. 아직 큰 수입을 기대하기에는 이르지만, 그래도 장래적인 관점에서 보면 꽤 중요한 시점이라고 할 수 있겠네요. 오늘은 자신의 능력을 키워나가는 시간을 가지는 것이 좋아 보여요. 너무 서두르지 말고 천천히, 그리고 꾸준히 노력하면 결과가 따라올 거예요.\\n오늘을 기점으로 당신의 미래를 위한 투자를 시작해 보세요. 물론, 오늘 급하게 큰 돈을 벌기를 바라는 것은 조금 이르다는 것을 명심하세요. 조금씩 천천",
  },
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  {
    id: 4,
    name: "THE DEVIL",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251518.png",
    summary: "물도 이렇게 마구 쓰진 않을텐데 반성하세요.",
    description:
      "오늘은 자신도 모르게 '흥청망청' 돈을 쓸 가능성이 높은 날이에요. 특히나 친구들과의 술자리에서 분위기에 휩쓸리면, 지출이 예상보다 늘어날 수 있어요. 이런 날은 재미있고 즐거운 시간을 보낼 수 있지만, 나중에 지출을 회상하며 후회하는 일이 없도록 주의가 필요해요. 그러니 오늘은 지출을 조절하는 것을 명심하세요. 이런 날에는 집에서 편안하게 시간을 보내는 것도 나쁘지 않은 선택이 될 수 있습니다. \\n현명한 결정을 내리시길 바라며, 오늘도 행운이 함께하",
  },
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  {
    id: 6,
    name: "ACE of PENTACLES",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251520.png",
    summary: "금전이 뒷받침되는 하루라니 부러워요",
    description:
      "오늘은 기대하지 않던 금전적인 기회가 찾아올 수 있는 특별한 날이네요! 아마도 새로운 아르바이트 기회 혹은 다른 수입 창구에서 긍정적인 변화가 있을 것으로 보입니다. 주변에서 부탁하는 일이 있다면, 도와주는 것도 행운을 불러올 수 있는 기회가 될 수 있어요. 평소에 생각하지 못했던 방법으로도 소소한 수입을 창출해 볼 수 있는 좋은 시간입니다. 애물단지에 있던 물건을 중고 시장에 내놓는 것도 하나의 방법이 될 수 있어요. \\n오늘의 키워드는 '유연성'입니",
  },
  null,
  null,
  null,
  {
    id: 14,
    name: "The Calm Before the Storm",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251517.png",
    summary: "금전 관리에 집중하는 것이 오늘 당신의 핵심 과제입니다.",
    description:
      "오늘은 금전 관리에 신경을 쓰는 것이 중요한 날이 될 것 같습니다. 갑작스러운 지출이나 미뤄두었던 부채 등이 당신 앞에 나타날 수 있어요. 이런 상황에서 중요한 것은 안정적인 금전 관리와 특별히 필요하지 않은 소비를 피하는 것입니다.\\n물론, 자금이 풍부하다면 문제가 없겠지만, 현재의 금융 상태를 고려하여 미래를 위한 저금 계획을 세우는 것이 현명한 선택일 것입니다. 당신이 현명한 결정을 내리며, 장기적인 안정을 위한 계획을 세운다면, 앞으로의 경제적인",
  },
  {
    id: 30,
    name: "EIGHT OF SWORDS",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251533.png",
    summary: "나만의 속박에서 벗어나는 첫걸음을 떼보세요.",
    description:
      "오늘 당신은 자신의 재물 관리나 지출에 대한 불안감이나 제한된 느낌을 받을 수 있어요. 아마도 예산을 초과해서 지출하게 될 상황이 올 수도 있습니다. 혹은 당신의 주변에서 금전적인 압박이나 부담을 주는 상황이 생길 수 있습니다.\\n이러한 상황 속에서도, 당신은 자신의 감정에 갇히지 않고 현실적인 해결책을 찾아야 합니다. 지출을 줄이기 위한 방법을 찾거나, 돈을 벌 수 있는 다른 경로를 탐색하는 것이 좋아요. 자신의 상황에 너무 답답해 하지 말고, 주변을",
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

const consumeData = [
  {
    id: 1,
    name: "basic1",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251530.png",
    createdDate: "2023-09-26T19:32:56",
    cardType: "BASIC",
  },
  {
    id: 3,
    name: "basic2",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251521.png",
    createdDate: "2023-09-26T19:38:09",
    cardType: "BASIC",
  },
  {
    id: 4,
    name: "basic3",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251522.png",
    createdDate: "2023-09-26T19:38:11",
    cardType: "BASIC",
  },
  {
    id: 5,
    name: "basic4",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251523.png",
    createdDate: "2023-09-26T19:38:12",
    cardType: "BASIC",
  },
  {
    id: 6,
    name: "basic5",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251524.png",
    createdDate: "2023-08-26T19:38:13",
    cardType: "BASIC",
  },
  {
    id: 7,
    name: "basic6",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251525.png",
    createdDate: "2023-08-26T19:38:14",
    cardType: "BASIC",
  },
  {
    id: 8,
    name: "basic7",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251526.png",
    createdDate: "2023-08-26T19:38:14",
    cardType: "BASIC",
  },
  {
    id: 9,
    name: "basic8",
    imagePath: "http://149.28.51.188/outputs/test%40gmail.com_202309251527.png",
    createdDate: "2023-08-26T19:38:15",
    cardType: "BASIC",
  },
];

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

  const getImagesByMonth = (month: string) => {
    return consumeData.filter((item) => {
      const itemMonth = item.createdDate.split("T")[0]; // Extract month from createdDate
      return itemMonth === month;
    });
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
              {/* Map over images for the specific month */}
              {getImagesByMonth("2023-09").map((item, index) => (
                <img
                  key={index}
                  src={item.imagePath}
                  alt={`Image ${index + 1}`}
                />
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
              {data.map((card, index) =>
                card ? (
                  <TarotCard
                    key={index}
                    width="140px"
                    height="80%"
                    cardWidth="100%"
                    cardSrc={frontcard}
                    imageSrc={card.imagePath}
                    bottomImageWidth="100%"
                    text={card.name}
                    fontsize="0.8rem"
                  />
                ) : (
                  <img
                    key={index}
                    src={cardBack}
                    style={{ width: "140px", marginBottom: "40px" }}
                  />
                )
              )}
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
