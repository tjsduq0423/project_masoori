import styled from "styled-components";

export interface StyledTarotCardProps {
  width: string;
  height: string;
  cardWidth: string;
  cardSrc: string;
  imageSrc: string;
  bottomImageWidth: string;
  text: string;
  fontsize: string;
  bottom?: string;
  onClick?: () => void;
}

const StyledTarotCardContainer = styled.div<{ width: string; height: string }>`
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  position: relative;
`;

const StyledTarotCardTop = styled.div<{ cardWidth: string }>`
  width: ${(props) => props.cardWidth};
  height: auto;
  position: absolute;
  z-index: 1;

  transition: transform 0.2s ease; /* 호버 애니메이션 추가 */
  transform-origin: center center;

  /* 호버 시 확대 스타일 */
  &:hover {
    transform: scale(1.02);
    box-shadow: 0 0 20px rgba(255, 138, 208, 0.8);
    border-radius: 15px;
  }
`;

const TopImage = styled.img<{ cardWidth: string }>`
  width: ${(props) => props.cardWidth};
  height: auto;
`;

const StyledTarotCardBottom = styled.div<{ cardWidth: string }>`
  width: 97.5%;
  position: relative;
  z-index: 0;
  margin: 0 auto;
  height: 100%;
  overflow: hidden;
`;

const BottomImage = styled.img<{ bottomImageWidth: string }>`
  width: ${(props) => props.bottomImageWidth};
  border-radius: 20px;
  height: auto;
`;

const BottomText = styled.div<{
  text: string;
  fontsize: string;
  bottom: string;
}>`
  position: absolute;
  width: 100%;
  bottom: ${(props) => props.bottom};
  left: 50%;
  transform: translateX(-50%);
  z-index: 1;
  font-size: ${(props) => props.fontsize};
  color: #5e3a66;
  font-family: "Museum";
`;

const TarotCard = ({
  width,
  height,
  cardWidth,
  cardSrc,
  imageSrc,
  bottomImageWidth,
  text,
  bottom = "5px",
  fontsize,
  onClick,
}: StyledTarotCardProps) => {
  return (
    <StyledTarotCardContainer width={width} height={height} onClick={onClick}>
      <StyledTarotCardTop cardWidth={cardWidth}>
        <TopImage cardWidth={cardWidth} src={cardSrc}></TopImage>
        <BottomText text={text} fontsize={fontsize} bottom={bottom}>
          {text}
        </BottomText>
      </StyledTarotCardTop>
      <StyledTarotCardBottom cardWidth={cardWidth}>
        <BottomImage
          bottomImageWidth={bottomImageWidth}
          src={imageSrc}
        ></BottomImage>
      </StyledTarotCardBottom>
    </StyledTarotCardContainer>
  );
};

export default TarotCard;
