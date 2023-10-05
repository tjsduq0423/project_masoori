import { StyledChallengeBubbleProps } from "@/types/challengeType";
import styled from "styled-components";

const StyledChallengeBubble = styled.div<StyledChallengeBubbleProps>`
  width: ${(props) => props.width};
  border-radius: ${(props) => props.borderRadius};
  background: ${(props) => props.background};
  opacity: ${(props) => props.opacity};
  box-shadow:
    0px 5px 10px 0px rgba(255, 255, 255, 0.1) inset,
    5px 0px 10px 0px rgba(255, 255, 255, 0.1) inset,
    0px -5px 10px 0px rgba(255, 255, 255, 0.1) inset,
    -5px 0px 10px 0px rgba(255, 255, 255, 0.1) inset;
  padding-left: ${(props) => props.paddingLeftRight};
  padding-right: ${(props) => props.paddingLeftRight};
  padding-top: ${(props) => props.paddingTopBottom};
  padding-bottom: ${(props) => props.paddingTopBottom};
  word-wrap: break-word;
  text-align: left;
  color: white;
  position: relative;
  display: flex;
  align-items: center;
`;

const BubbleContainer = styled.div``;

const Image = styled.img`
  width: 70px;
`;

const ContentContainer = styled.div`
  margin-left: 15px;
  color: #ffd2d2;
`;

const Title = styled.div`
  font-weight: bold;
  font-size: 18px;
  color: white;
  margin-bottom: 3px;
`;

const ChallengeBubble = ({
  text,
  width,
  background,
  opacity,
  paddingLeftRight,
  paddingTopBottom,
  borderRadius,
  imgLink,
  titleText,
}: StyledChallengeBubbleProps) => {
  return (
    <BubbleContainer>
      <StyledChallengeBubble
        width={width}
        paddingLeftRight={paddingLeftRight}
        paddingTopBottom={paddingTopBottom}
        background={background}
        opacity={opacity}
        borderRadius={borderRadius}
        text={text}
        titleText={titleText}
      >
        <Image src={imgLink}></Image>
        <ContentContainer>
          <Title>{titleText}</Title>
          {text}
        </ContentContainer>
      </StyledChallengeBubble>
    </BubbleContainer>
  );
};

export default ChallengeBubble;
