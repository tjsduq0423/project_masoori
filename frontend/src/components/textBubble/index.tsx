import styled from "styled-components";

export interface StyledTextBubbleProps {
  text: string;
  width: string;
  background: string;
  opacity: string;
  paddingLeftRight: string;
  paddingTopBottom: string;
}

const StyledTextBubble = styled.div<StyledTextBubbleProps>`
  width: ${(props) => props.width};
  border-radius: 44px;
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
`;

const TextBubble = ({
  text,
  width,
  background,
  opacity,
  paddingLeftRight,
  paddingTopBottom,
}: StyledTextBubbleProps) => {
  return (
    <StyledTextBubble
      width={width}
      text={text}
      paddingLeftRight={paddingLeftRight}
      paddingTopBottom={paddingTopBottom}
      background={background}
      opacity={opacity}
    >
      {text}
    </StyledTextBubble>
  );
};

export default TextBubble;
