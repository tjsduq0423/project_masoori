import styled from "styled-components";

interface StyledButtonProps {
  text: string;
}

// StyledButton을 props로 width와 height를 받도록 변경
const StyledButton = styled.div<StyledButtonProps>`
  border-radius: 34.5px;
  background: rgba(47, 6, 6, 0.6);
  width: fit-content;
  padding: 10px 30px;
  color: #fff;
  font-size: 26px;
  font-weight: 500;
  text-align: center;
`;

const HashTag = ({ text }: StyledButtonProps) => {
  return <StyledButton text={text}>☆ {text}</StyledButton>;
};

export default HashTag;
