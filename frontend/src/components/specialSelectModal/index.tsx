import { useAllChallengeCard } from "@/apis/dictionary/Queries/useAllChallengeCard";
import { specialIdState } from "@/states/dictionaryState";
import { useNavigate } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import styled from "styled-components";

export interface StyledSpecialSelectModalProps {
  toggleModal: () => void;
}

interface AllChallengeProps {
  id: number;
  name: string;
  imagePath: string;
  createdDate: string;
  cardType: string;
}

const Container = styled.div<StyledSpecialSelectModalProps>`
  display: grid;
  grid-template-columns: repeat(4, minmax(275px, 1fr));
  grid-gap: 2rem;
  margin: 2rem;
`;

const Card = styled.div`
  height: 215px;
  position: relative;
  overflow: hidden;
  border-radius: 8px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
  display: grid;
  grid-template-rows: 1fr 1fr;
  transition: 0.8s cubic-bezier(0.2, 0.8, 0.2, 1);

  &:hover {
    transform: scale(1.035, 1.035);
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.5);
  }
`;

const H3 = styled.h3`
  color: white;
  font-size: 24px;
  margin: 20px 0 0 20px;
  text-align: left;
`;

const P = styled.p`
  color: white;
  font-weight: 400;
  font-size: 16px;
  align-self: end;
  margin: 0 0 20px 20px;
  letter-spacing: 0.5px;
  text-align: left;
`;

const Img = styled.img`
  position: absolute;
  top: 0;
  height: 110%;
  width: 100%;
  z-index: -1;
  transition: 0.8s cubic-bezier(0.2, 0.8, 0.2, 1);

  ${Card}:hover & {
    transform: translateY(-10px);
  }
`;

function extractYearAndMonth(createdDate: string) {
  // createdDate 문자열을 Date 객체로 변환
  const date = new Date(createdDate);

  // 년도와 월을 추출
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.

  return { year, month };
}

const SpecialSelectModal = ({ toggleModal }: StyledSpecialSelectModalProps) => {
  const currentDate = new Date();
  currentDate.setHours(0, 0, 0, 0); // Set hours, minutes, seconds, and milliseconds to zero
  const initialEndDate = currentDate.toISOString().slice(0, -2);

  const AllChallenge = useAllChallengeCard(
    "2000-09-16T07:42:34.76",
    initialEndDate
  ).userCardList;

  const navigate = useNavigate();

  const setSpecialId = useSetRecoilState(specialIdState);
  const goCardSpecial = (id: number) => {
    setSpecialId(id);
    toggleModal();
    navigate("/dictionary");
  };

  return (
    <Container toggleModal={toggleModal}>
      {AllChallenge.map((card: AllChallengeProps) => (
        <Card key={card.id} onClick={() => goCardSpecial(card.id)}>
          <Img src={card.imagePath} alt="Card Image" />
          <H3>{card.name}</H3>
          <P>
            {extractYearAndMonth(card.createdDate).year}년{" "}
            {extractYearAndMonth(card.createdDate).month}월
          </P>
        </Card>
      ))}
    </Container>
  );
};

export default SpecialSelectModal;
