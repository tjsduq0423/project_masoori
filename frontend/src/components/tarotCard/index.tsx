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
  z-index: 2;
`;

const TopImage = styled.img<{ cardWidth: string }>`
  width: ${(props) => props.cardWidth};
  height: auto;
`;

const StyledTarotCardBottom = styled.div<{ cardWidth: string }>`
  width: 97.5%;
  position: relative;
  z-index: 1;
  margin: 0 auto;
  height: 100%;
  overflow: hidden;
`;

const BottomImage = styled.img<{ bottomImageWidth: string }>`
  width: ${(props) => props.bottomImageWidth};
  height: auto;
`;

const BottomText = styled.div<{ text: string; fontsize: string }>`
  position: relative;
  z-index: 3;
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
  fontsize,
}: StyledTarotCardProps) => {
  return (
    <StyledTarotCardContainer width={width} height={height}>
      <StyledTarotCardTop cardWidth={cardWidth}>
        <TopImage cardWidth={cardWidth} src={cardSrc}></TopImage>
      </StyledTarotCardTop>
      <StyledTarotCardBottom cardWidth={cardWidth}>
        <BottomImage
          bottomImageWidth={bottomImageWidth}
          src={imageSrc}
        ></BottomImage>
      </StyledTarotCardBottom>
      <BottomText text={text} fontsize={fontsize}>
        {text}
      </BottomText>
    </StyledTarotCardContainer>
  );
};

// const StyledCard = styled.div<StyledTarotCardProps>``;

// const StyledTarotCard = styled.div<StyledTarotCardProps>`
//   width: ${(props) => props.cardWidth};
//   height: 100%;
//   position: relative;
//   background-image: url(${(props) => props.cardSrc});
//   background-size: cover;
// `;

// const StyledTarotCardImage = styled.div<StyledTarotCardProps>`
//   position: absolute;
//   top: 0;
//   left: 0;
//   width: ${(props) => props.imageWidth}; // imageWidth로 수정
//   height: 100%;
//   z-index: 0;
//   background-image: url(${(props) => props.imageSrc});
//   background-size: cover;
// `;

// const TarotCard = ({
//   cardWidth,
//   cardSrc,
//   imageWidth,
//   imageSrc,
// }: StyledTarotCardProps) => {
//   return (
//     <StyledCard
//       cardWidth={cardWidth}
//       cardSrc={cardSrc}
//       imageWidth={imageWidth}
//       imageSrc={imageSrc}
//     >
//       <StyledTarotCard cardWidth={cardWidth} cardSrc={cardSrc}>
//         <StyledTarotCardImage
//           imageWidth={imageWidth}
//           imageSrc={imageSrc}
//         ></StyledTarotCardImage>
//       </StyledTarotCard>
//     </StyledCard>
//   );
// };

export default TarotCard;
