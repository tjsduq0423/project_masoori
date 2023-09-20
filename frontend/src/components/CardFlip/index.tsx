import React, { useState } from "react";
import { useSpring, a } from "@react-spring/web";
import styled from "styled-components";

import cardFront from "@/assets/img/cardFront.png";
import cardBack from "@/assets/img/cardBack.png";

const CardContainer = styled(a.div)`
  position: absolute;
  max-width: 500px;
  max-height: 500px;
  width: 200px;
  height: 300px;
  cursor: pointer;
  border-radius: 10px;
  will-change: transform, opacity;
`;

const Back = styled(CardContainer)`
  background-size: cover;
  background-image: url(${cardBack});
`;

const Front = styled(CardContainer)`
  background-size: cover;
  background-image: url(${cardFront});
`;

interface CardFlipProps {
  onClick?: () => void;
  isClickable?: boolean;
}

const CardFlip: React.FC<CardFlipProps> = ({ onClick, isClickable = true }) => {
  const [flipped, setFlipped] = useState(false);

  const { transform, opacity } = useSpring({
    opacity: flipped ? 1 : 0,
    transform: `perspective(600px) rotateY(${flipped ? 180 : 0}deg)`,
    config: { mass: 5, tension: 500, friction: 80 },
  });

  const handleClick = () => {
    if (isClickable && !flipped) {
      setFlipped(true);
      if (onClick) {
        onClick();
      }
    }
  };

  return (
    <div>
      <Back
        style={{
          opacity: opacity.to((o) => 1 - o),
          transform,
        }}
        onClick={handleClick}
      />
      <Front
        style={{
          opacity,
          transform,
          rotateY: "180deg",
        }}
        onClick={handleClick}
      />
    </div>
  );
};

export default CardFlip;
