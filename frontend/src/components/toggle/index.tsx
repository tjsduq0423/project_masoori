import React, { useState, useEffect } from "react";
import styled, { keyframes } from "styled-components";

interface TextProps {
  isChecked: boolean;
}

interface ToggleSwitchProps {
  textOn: string;
  textOff: string;
  backgroundImage: string;
  backgroundColor: string;
  checked: boolean;
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
    left: 235px;
    width: 52px;
  }
`;

const reverseAnimation = keyframes`
  0% {
    left: 235px;
    width: 52px;
  }
  60% {
    left: 150px;
    width: 112px;
  }
  100% {
    left: 4px;
  }
`;

const ToggleLabel = styled.label<{ bgImage: string; bgColor: string }>`
  height: 62.048px;
  width: 100%;
  border-radius: 100px;
  background: white;
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
    width: 52px;
    height: 52px;
    top: 6px;
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
    top: 5px;
    right: 4px;
    width: 52px;
    height: 52px;
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
  background: "#ff0e0e";
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
  font-size: 18px;
  font-weight: bold;
  margin-left: ${(props) => (props.isChecked ? "15%" : "25%")};
`;

const ToggleSwitch = ({
  textOn,
  textOff,
  backgroundImage,
  backgroundColor,
  checked,
}: ToggleSwitchProps) => {
  const [isChecked, setIsChecked] = useState(checked);

  useEffect(() => {
    setIsChecked(checked); // Update the isChecked state when the 'checked' prop changes
  }, [checked]);

  const toggleSwitch = () => {
    setIsChecked(!isChecked);
  };

  const toggleId = Math.random().toString(36).substring(7);

  return (
    <>
      <ToggleCheckbox
        id={`toggle-${toggleId}`}
        checked={isChecked}
        onChange={toggleSwitch}
      />
      <ToggleLabel
        htmlFor={`toggle-${toggleId}`}
        bgImage={backgroundImage}
        bgColor={backgroundColor}
      >
        <ToggleLabelBackground />
        <Text isChecked={isChecked}>{isChecked ? textOn : textOff}</Text>
      </ToggleLabel>
    </>
  );
};

export default ToggleSwitch;
