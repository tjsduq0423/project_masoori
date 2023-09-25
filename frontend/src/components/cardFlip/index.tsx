import React, { useState } from "react";
import { useSpring, a } from "@react-spring/web";
import styled from "styled-components";
import TarotCard from "@/components/tarotCard";

import tarotCardFront from "@/assets/img/tarotCard/tarotCardFront.png";
import cardBack from "@/assets/img/cardBack.png";

const CardContainer = styled(a.div)`
  position: absolute;
  max-width: 500px;
  max-height: 500px;
  width: 265px;
  height: 400px;
  cursor: pointer;
  border-radius: 10px;
  margin-left: -9%;
  margin-top: 2%;
  will-change: transform, opacity;
`;

const Back = styled(CardContainer)`
  background-size: cover;
  background-image: url(${cardBack});
`;

const Front = styled(CardContainer)``;

interface CardFlipProps {
  onClick?: () => void;
  isClickable?: boolean;
  imageSrc?: string;
  text?: string;
}

const CardFlip = ({
  onClick,
  isClickable = true,
  imageSrc = "",
  text = "",
}: CardFlipProps) => {
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
      >
        <TarotCard
          width="265px"
          height="355px"
          cardWidth="100%"
          cardSrc={tarotCardFront}
          imageSrc={imageSrc}
          bottomImageWidth="100%"
          text={text}
          fontsize="20px"
        ></TarotCard>
      </Front>
    </div>
  );
};

export default CardFlip;
