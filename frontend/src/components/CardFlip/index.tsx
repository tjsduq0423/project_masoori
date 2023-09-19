import React, { useState } from "react";
import { useSpring, a } from "@react-spring/web";
import styled from "styled-components";

import background from "@/assets/img/background/capetBackground.jpg";
import cardFront from "@/assets/img/cardFront.png";
import cardBack from "@/assets/img/cardBack.png";

// Define styled components for the card and its faces
const Container = styled.div`
  display: flex;
  align-items: center;
  height: 100%;
  justify-content: center;
  background-image: url(${background});
  background-size: cover;
`;

const Card = styled(a.div)`
  position: absolute;
  max-width: 500px;
  max-height: 500px;
  width: 200px;
  height: 300px;
  cursor: pointer;
  border-radius: 10px;
  will-change: transform, opacity;
`;

const Back = styled(Card)`
  background-size: cover;
  background-image: url(${cardBack});
`;

const Front = styled(Card)`
  background-size: cover;
  background-image: url(${cardFront});
`;

const CardFlip = () => {
  const [flipped, set] = useState(false);

  const { transform, opacity } = useSpring({
    opacity: flipped ? 1 : 0,
    transform: `perspective(600px) rotateY(${flipped ? 180 : 0}deg)`,
    config: { mass: 5, tension: 500, friction: 80 },
  });

  return (
    <Container>
      <Back
        style={{
          opacity: opacity.to((o) => 1 - o),
          transform,
        }}
        onClick={() => set((state) => !state)}
      />
      <Front
        style={{
          opacity,
          transform,
          rotateY: "180deg",
        }}
        onClick={() => set((state) => !state)}
      />
    </Container>
  );
};

export default CardFlip;
