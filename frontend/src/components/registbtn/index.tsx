import styled from "styled-components";

// StyledButton을 props로 width와 height를 받도록 변경
const StyledButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  background: #cab2d3;
  width: 18.5rem;
  height: 5.2rem;

  /* 호버 시 스타일 변경 */
  &:hover {
    background: #813e83; /* 호버 시 배경색 변경 */
    color: #fff; /* 호버 시 글씨색 변경 */
  }
`;

const StyledText = styled.div`
  color: #813e83;
  font-size: 2.2rem;
  font-weight: 700;
  margin-right: 10px;

  ${StyledButton}:hover & {
    color: #fff; /* 호버 시 글씨색 변경 */
  }
`;

const StyledSVG = styled.svg`
  fill: #813e83;

  ${StyledButton}:hover & {
    fill: #fff;
  }
`;

interface RegistBtnProps {
  onClick?: () => void; // Define the onClick prop
}

const RegistBtn = ({ onClick }: RegistBtnProps) => {
  return (
    <StyledButton onClick={onClick}>
      <StyledText>신청하러 가기</StyledText>
      <StyledSVG
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="44"
        viewBox="0 0 26 44"
        fill="none"
      >
        <path d="M0 4.664L16.9043 22L0 39.336L4.54785 44L26 22L4.54785 0L0 4.664Z" />
      </StyledSVG>
    </StyledButton>
  );
};

export default RegistBtn;
