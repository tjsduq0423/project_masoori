import React, { useState } from "react";
import styled, { keyframes } from "styled-components";

interface TextProps {
  isChecked: boolean;
}

interface ToggleSwitchProps {
  textOn: string;
  textOff: string;
  backgroundImage: string;
  backgroundColor: string;
}

const switchAnimation = keyframes`
  0% {
    left: 4px;
  }
  60% {
    left: 4px;
    width: 112px;
  }
  100% {
    left: 304px;
    width: 82px;
  }
`;

const reverseAnimation = keyframes`
  0% {
    left: 304px;
    width: 82px;
  }
  60% {
    left: 264px;
    width: 112px;
  }
  100% {
    left: 4px;
  }
`;

const ToggleLabel = styled.label<{ bgImage: string; bgColor: string }>`
  width: 400px;
  height: 100px;
  border-radius: 100px;
  border: 5px solid #72cce3;
  display: flex;
  align-items: center;
  position: relative;
  transition: all 150ms ease-in;

  &:before {
    animation-name: ${reverseAnimation};
    animation-duration: 350ms;
    animation-fill-mode: forwards;
    transition: all 150ms ease-in;
    content: "";
    width: 82px;
    height: 82px;
    top: 4px;
    left: 4px;
    position: absolute;
    border-radius: 82px;
    box-shadow: inset 0 -0.125em 0.25em rgba(0, 0, 0, 0.2);
  }

  &:after {
    transition-delay: 0ms;
    transition: all 150ms ease-in;
    position: absolute;
    content: "";
    top: 4px;
    right: 4px;
    width: 82px;
    height: 82px;
    background: url(${(props) => props.bgImage}) ${(props) => props.bgColor};
    background-size: 63.6363636364% 54.5454545455%;
    background-position: center;
    background-repeat: no-repeat;
    box-shadow: inset 0 -0.125em 0.25em rgba(0, 0, 0, 0.2);
    image-rendering: pixelated;
    border-radius: 50%;
    opacity: 0;
  }
`;

const ToggleLabelBackground = styled.div`
  width: 10px;
  height: 5px;
  border-radius: 5px;
  position: relative;
  background: "#fff";
  left: 135px;
  top: 45px;
  transition: all 150ms ease-in;

  &:before,
  &:after {
    content: "";
    position: absolute;
    width: 40px;
    height: 5px;
    border-radius: 5px;
    background: "#fff";
    transition: all 150ms ease-in;
  }

  &:before {
    top: -5px;
    left: -20px;
  }

  &:after {
    top: 5px;
    left: -10px;
  }
`;

const ToggleCheckbox = styled.input.attrs({ type: "checkbox" })`
  display: none;

  &:checked + ${ToggleLabel} ${ToggleLabelBackground} {
    left: 60px;
    width: 5px;
  }

  &:checked + ${ToggleLabel} ${ToggleLabelBackground}:before {
    width: 5px;
    height: 5px;
    top: -25px;
  }

  &:checked + ${ToggleLabel} ${ToggleLabelBackground}:after {
    width: 5px;
    height: 5px;
    left: -30px;
    top: 20px;
  }

  &:checked + ${ToggleLabel}:before {
    background: "#fff";
    animation-name: ${switchAnimation};
    animation-duration: 350ms;
    animation-fill-mode: forwards;
  }

  &:checked + ${ToggleLabel}:after {
    transition-delay: 350ms;
    opacity: 1;
  }
`;

const Text = styled.div<TextProps>`
  transition: all 0.3s ease-in-out;
  color: #a37c9b;
  font-family: Pretendard;
  font-size: 22px;
  font-weight: bold;
  margin-left: ${(props) => (props.isChecked ? "15%" : "40%")};
`;

const ToggleSwitch = ({
  textOn,
  textOff,
  backgroundImage,
  backgroundColor,
}: ToggleSwitchProps) => {
  const [isChecked, setIsChecked] = useState(false);

  const toggleSwitch = () => {
    setIsChecked(!isChecked);
  };

  return (
    <div className="container">
      <ToggleCheckbox id="toggle" checked={isChecked} onChange={toggleSwitch} />
      <ToggleLabel
        htmlFor="toggle"
        bgImage={backgroundImage}
        bgColor={backgroundColor}
      >
        <ToggleLabelBackground />
        <Text isChecked={isChecked}>{isChecked ? textOn : textOff}</Text>
      </ToggleLabel>
    </div>
  );
};

export default ToggleSwitch;
