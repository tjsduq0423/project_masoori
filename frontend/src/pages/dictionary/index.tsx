import React, { useState, useEffect } from "react";
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
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import {
  creditInfoState,
  specialIdState,
  specialImageUrlState,
  spendIdState,
} from "@/states/dictionaryState";
import { useNavigate } from "react-router-dom";
import { useProfileImage } from "@/apis/menu/Mutations/useProfileImage";
import { toast } from "react-toastify";
import Lottie from "lottie-react";
import { useAllChallengeCard } from "@/apis/dictionary/Queries/useAllChallengeCard";

import cardBack from "@/assets/img/tarotCard/tarotCardBack.png";

import { useChallengeCard } from "@/apis/dictionary/Queries/useChallengeCard";
import { useAllUserFortune } from "@/apis/luck/Queries/useAllUserFortune";
import { useGetAllConsume } from "@/apis/dictionary/Queries/useGetAllConsume";
import SpecialSelectModal from "@/components/specialSelectModal";
import card from "@/assets/img/cardFront.png";

interface Challenge {
  id: number;
  isSuccess: boolean;
  name: string;
  achievementCondition: string;
  startTime: string;
  endTime: string;
}

interface FortuneListProps {
  id: number;
  name: string;
  imagePath: string;
  summary: string;
  description: string;
}

interface AllConsumeProps {
  id: number;
  name: string;
  imagePath: string;
  createdDate: string;
  cardType: string;
}

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
  padding-left: 20px;
  margin-top: 5%;
  overflow: auto;
  margin-bottom: 300px;
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
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSpecialModalOpen, setIsSpecialModalOpen] = useState(false);
  const [specialId, setSpecialId] = useRecoilState(specialIdState);
  const profileImage = useProfileImage();

  const currentDate = new Date();
  currentDate.setHours(0, 0, 0, 0); // Set hours, minutes, seconds, and milliseconds to zero
  const initialEndDate = currentDate.toISOString().slice(0, -2);

  const setCreditInfo = useSetRecoilState(creditInfoState);
  const setSpendId = useSetRecoilState(spendIdState);
  const setSpecialImageUrl = useSetRecoilState(specialImageUrlState);

  const challengeCard = useChallengeCard(specialId);

  setSpecialImageUrl(challengeCard.card.imagePath);
  const allUserFortune = useAllUserFortune().fortuneList;

  console.log(allUserFortune);

  const AllConsume = useGetAllConsume(
    "2000-09-16T07:42:34.76",
    initialEndDate
  ).userCardList;

  const navigate = useNavigate();

  const groupImagesByMonth = () => {
    const months: Record<string, any[]> = {};

    AllConsume.forEach((item: AllConsumeProps) => {
      const dateParts = item.createdDate.split("T")[0].split("-");
      const year = dateParts[0];
      const month = dateParts[1];

      const formattedDate = `${year}.${month}`; // 년도와 월을 결합

      if (!months[formattedDate]) {
        months[formattedDate] = []; // 해당 월의 배열을 초기화
      }

      months[formattedDate].push(item); // 해당 월에 데이터 추가
    });

    return months;
  };

  // 모달 열기 함수
  const openModal = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 함수
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const openSpecialModal = () => {
    setIsSpecialModalOpen(true);
  };

  // 모달 닫기 함수
  const closeSpecialModal = () => {
    setIsSpecialModalOpen(false);
  };

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const goCardRecommend = (month: string) => {
    const [year, monthNum] = month.split(".");
    const formattedMonth = `${year}-${monthNum}-01T00:00:00`;
    setCreditInfo(formattedMonth);
    navigate("/card");
  };

  const goCardSpend = (id: number) => {
    setSpendId(id);
    navigate("/spend");
  };

  // 모달 열기 함수
  const toggleModal: () => void = () => {
    setIsSpecialModalOpen(!isSpecialModalOpen);
  };

  const settingProfileImage = async () => {
    try {
      // 이름과 전화번호를 사용하여 SMS를 보냅니다.
      await profileImage.mutateAsync(challengeCard.card.id);
      toast.info("🃏 프로필 카드 등록 완료 🃏");
    } catch (error) {
      console.error("인증 코드 전송 실패:", error);
    }
  };

  const crystalChallengeBubbleProps: StyledChallengeBubbleProps = {
    text: `사실 말도 안되는 챌린지죠 그치만 어쩌겠습니까 해야지`,
    width: "302px",
    background: "#4D1B2D80",
    opacity: "1",
    paddingLeftRight: "40px",
    paddingTopBottom: "30px",
    borderRadius: "20px",
    imgLink: ChallegeSuccess,
    titleText: "소비금액 5만원 넘지 않기",
  };

  const formatSpecialDateString = (SpecialdateString: string) => {
    const date = new Date(SpecialdateString);
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
    const formatSpecialDateString = `${year}.${month
      .toString()
      .padStart(2, "0")}`;
    return formatSpecialDateString;
  };

  const formattedSpecialDate = formatSpecialDateString(
    challengeCard.card.createdDate
  );

  return (
    <PageContainer>
      <BookSection>
        <BookTitle>Collection Book</BookTitle>
        <Book onPageChange={handlePageChange} />
      </BookSection>
      <ContentSection>
        {currentPage === 0 && (
          <div style={{ marginBottom: "140px", marginTop: "30px" }}>
            {Object.keys(groupImagesByMonth()).map((month) => (
              <div key={month}>
                <BasicText>
                  {month}
                  <DcitBtn
                    text="카드추천"
                    onClick={() => goCardRecommend(month)}
                  />
                </BasicText>
                <div
                  style={{
                    display: "grid",
                    gridTemplateColumns: "repeat(5, 1fr)",
                    marginTop: "2rem",
                    marginBottom: "40px",
                  }}
                >
                  {groupImagesByMonth()[month].map((item, index) => {
                    return (
                      <TarotCard
                        key={index}
                        width="140px"
                        height="200px"
                        cardWidth="100%"
                        cardSrc={frontcard}
                        imageSrc={item.imagePath}
                        bottomImageWidth="100%"
                        text={item.name}
                        fontsize="0.8rem"
                        onClick={() => goCardSpend(item.id)}
                      />
                    );
                  })}
                </div>
              </div>
            ))}
          </div>
        )}
        {currentPage === 2 && (
          <div style={{ marginTop: "0px" }}>
            <SpecialHeader>
              <SpecialText>
                {formattedSpecialDate}
                <DcitBtn onClick={settingProfileImage} text="프로필 설정" />
                <DcitBtn onClick={openSpecialModal} text="카드변경" />
                <DcitBtn onClick={openModal} text="공유하기" />
              </SpecialText>
            </SpecialHeader>
            <div style={{ display: "flex" }}>
              {challengeCard.challengeList.every(
                (challenge: Challenge) => challenge.isSuccess
              ) ? (
                // 모든 챌린지의 isSuccess가 true인 경우 Pokemon 카드 렌더링
                <PokemonCard
                  cardWidth="100%"
                  cardSrc={frontcard}
                  imageUrl={challengeCard.card.imagePath}
                  text={challengeCard.card.name}
                  bottom="20px"
                  fontsize="32px"
                />
              ) : (
                // 하나 이상의 챌린지의 isSuccess가 false인 경우 Tarot 카드 렌더링
                <div
                  style={{
                    marginRight: "30px",
                    backgroundColor: "rgba(0, 0, 0, 0.7)",
                    borderRadius: "20px",
                  }}
                >
                  <TarotCard
                    width="403px"
                    height="100%"
                    cardWidth="100%"
                    cardSrc={frontcard}
                    imageSrc={challengeCard.card.imagePath}
                    bottomImageWidth="100%"
                    text={challengeCard.card.name}
                    bottom="20px"
                    fontsize="32px"
                  />
                </div>
              )}
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-around",
                }}
              >
                {challengeCard.challengeList.map(
                  (challenge: Challenge, index: number) => (
                    <ChallengeBubble
                      key={index}
                      text={challenge.achievementCondition}
                      titleText={challenge.name}
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
                  )
                )}
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
              {allUserFortune.map((card: FortuneListProps, index: number) =>
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
      <ModalContainer isOpen={isSpecialModalOpen}>
        <SpecialSelectModal toggleModal={toggleModal} />
      </ModalContainer>
      <Backdrop isOpen={isSpecialModalOpen} onClick={closeSpecialModal} />
    </PageContainer>
  );
};

export default DictionaryPage;
