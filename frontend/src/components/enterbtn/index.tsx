import styled from "styled-components";

// StyledButton을 props로 width와 height를 받도록 변경
const StyledButton = styled.button`
  border-radius: 35.5px;
  background: #cab2d3;
  width: 188px;
  height: 71px;
  color: #813e83;
  font-family: "Pyeongchangpeace";
  font-size: 32px;
  font-weight: 700;
`;

const EnterBtn = () => {
  return <StyledButton>ENTER</StyledButton>;
};

export default EnterBtn;
