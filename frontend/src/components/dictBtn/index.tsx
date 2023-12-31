import React from "react";
import styled from "styled-components";

interface StyledButtonProps {
  text: string;
  width?: string;
  height?: string;
  onClick?: () => void;
}

const DictionaryButton = styled.button<StyledButtonProps>`
  border-radius: 35.5px;
  background: #d39090;
  width: ${(props) => props.width || "96px"};
  height: ${(props) => props.height || "30px"};
  color: #3e0f0e;
  font-family: "Pyeongchangpeace";
  font-size: 16px;
  font-weight: 700;
  margin-left: 10px;

  &:hover {
    background-color: #901818; /* 호버 시 배경색 변경 */
    color: white;
  }
`;

const DcitBtn = ({ text, width, height, onClick }: StyledButtonProps) => {
  return (
    <DictionaryButton
      width={width}
      height={height}
      text={text}
      onClick={onClick}
    >
      {text}
    </DictionaryButton>
  );
};

export default DcitBtn;
