import { StyledTextBubbleProps } from "@/types/luckType";
import styled from "styled-components";
import TextBubble from "../textBubble";
import ghost from "@/assets/img/ghost/ghost.png";

export interface StyledGhostModalProps {
  zIndex?: string;
  toggleModal: () => void;
}

const ModalBackground = styled.div<StyledGhostModalProps>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: ${(props) => props.zIndex};
`;

const ModalContainer = styled.div`
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 */
  flex-direction: row; /* 수평 정렬로 변경 */
`;

const Image = styled.img`
  width: 400px;
  height: 400px;
  margin: 80px;
`;

const TextBubbleContainer = styled.div`
  margin-bottom: 10px; /* 각 텍스트 버블 사이의 간격 조정 */
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 25px 0px 25px 0px;
`;

const titleTextBubbleProps: StyledTextBubbleProps = {
  text: "👻 유령 👻",
  width: "136PX",
  background: "#4D1B2D80",
  opacity: "1",
  paddingLeftRight: "28px",
  paddingTopBottom: "15px",
  borderRadius: "10px",
  hoverable: false,
};

const contentTextBubbleProps: StyledTextBubbleProps = {
  text: `안녕! >▽< ✨
  혹시 혹시  내가 너랑 계속 같이 있어도 될까?!?!! 나랑 다니면 좋을거야!!!!!
  너가 타로 보는 걸 잊더라도 내가 따라다니면서 알려줄 수도 있고!!!🃏🃏
  네 소비를 내가 바라보면서 마녀님에게 속삭여줄 수도 있어!!!!!🥴
  어때?? 나랑 친구 안 할래?? 나는 너랑 계속 같이 있고 싶은데 응응응?? 안돼? 응???🥺
  제발 제발 응??? 부탁할게!!!!🙏🙏✨`,
  width: "700px",
  background: "#4D1B2D80",
  opacity: "1",
  paddingLeftRight: "40px",
  paddingTopBottom: "40px",
  borderRadius: "20px",
  hoverable: false,
};

const crystalTextBubbleProps: StyledTextBubbleProps = {
  text: `유령의 제안을 수락하고 계정을 연동한다. ( 일주일 간 소비패턴을 분석하고 알림을 보냅니다 )`,
  width: "700px",
  background: "#4D1B2D80",
  opacity: "1",
  paddingLeftRight: "40px",
  paddingTopBottom: "20px",
  borderRadius: "20px",
  hoverable: true,
};

const shareTextBubbleProps: StyledTextBubbleProps = {
  text: `유령이 울든말든 친구를 해주지 않는다.`,
  width: "700px",
  background: "#4D1B2D80",
  opacity: "1",
  paddingLeftRight: "40px",
  paddingTopBottom: "20px",
  borderRadius: "20px",
  hoverable: true,
};

const GhostModal = ({ zIndex, toggleModal }: StyledGhostModalProps) => {
  const handleShareClick = () => {
    toggleModal(); // 공유 텍스트 버블을 클릭하면 모달 상태를 토글합니다.
  };

  return (
    <ModalBackground zIndex={zIndex} toggleModal={toggleModal}>
      <ModalContainer>
        <Image src={ghost}></Image>
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
          <TextBubbleContainer onClick={handleShareClick}>
            <TextBubble {...shareTextBubbleProps} />
          </TextBubbleContainer>
        </ContentWrapper>
      </ModalContainer>
    </ModalBackground>
  );
};

export default GhostModal;
