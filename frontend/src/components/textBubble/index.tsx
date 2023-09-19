import styled from "styled-components";
import { StyledTextBubbleProps } from "@/types/luckType";

const StyledTextBubble = styled.div<StyledTextBubbleProps>`
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

  /* hoverable 프로퍼티가 true일 때만 hover 효과 적용 */
  ${(props) =>
    props.hoverable &&
    `
    &:hover::before {
      opacity: 1;
    }
    &:hover {
      cursor: pointer;
    }
  `}

  &::before {
    content: "▶";
    opacity: 0;
    position: absolute;
    top: 50%;
    left: 20px; /* 이 부분을 적절히 조정하세요 */
    transform: translateY(-50%);
    transition: opacity 0.3s ease-in-out;
  }
`;

const TextBubble = ({
  text,
  width,
  background,
  opacity,
  paddingLeftRight,
  paddingTopBottom,
  borderRadius,
  hoverable,
}: StyledTextBubbleProps) => {
  return (
    <StyledTextBubble
      width={width}
      paddingLeftRight={paddingLeftRight}
      paddingTopBottom={paddingTopBottom}
      background={background}
      opacity={opacity}
      borderRadius={borderRadius}
      text={text}
      hoverable={hoverable}
    >
      {text}
    </StyledTextBubble>
  );
};

export default TextBubble;
