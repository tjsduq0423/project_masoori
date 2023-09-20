import React, { useState } from "react";
import styled, { createGlobalStyle, keyframes } from "styled-components";

// Global styles
const GlobalStyle = createGlobalStyle`
  html, body {
    height: 100%;
    background-color: #333844;
    padding: 0;
    z-index: 1;
    transform: translate3d(0, 0, 0.1px);
  }
`;

// Keyframes for animations
const holoSparkle = keyframes`
  0%,
  100% {
    opacity: 0.75;
    background-position: 50% 50%;
    filter: brightness(1.2) contrast(1.25);
  }
  5%,
  8% {
    opacity: 1;
    background-position: 40% 40%;
    filter: brightness(0.8) contrast(1.2);
  }
  13%,
  16% {
    opacity: 0.5;
    background-position: 50% 50%;
    filter: brightness(1.2) contrast(0.8);
  }
  35%,
  38% {
    opacity: 1;
    background-position: 60% 60%;
    filter: brightness(1) contrast(1);
  }
  55% {
    opacity: 0.33;
    background-position: 45% 45%;
    filter: brightness(1.2) contrast(1.25);
  }
`;

const holoGradient = keyframes`
  0%,
  100% {
    opacity: 0.5;
    background-position: 50% 50%;
    filter: brightness(0.5) contrast(1);
  }
  5%,
  9% {
    background-position: 100% 100%;
    opacity: 1;
    filter: brightness(0.75) contrast(1.25);
  }
  13%,
  17% {
    background-position: 0% 0%;
    opacity: 0.88;
  }
  35%,
  39% {
    background-position: 100% 100%;
    opacity: 1;
    filter: brightness(0.5) contrast(1);
  }
  55% {
    background-position: 0% 0%;
    opacity: 1;
    filter: brightness(0.75) contrast(1.25);
  }
`;

const holoCard = keyframes`
  0%,
  100% {
    transform: rotateZ(0deg) rotateX(0deg) rotateY(0deg);
  }
  5%,
  8% {
    transform: rotateZ(0deg) rotateX(6deg) rotateY(-20deg);
  }
  13%,
  16% {
    transform: rotateZ(0deg) rotateX(-9deg) rotateY(32deg);
  }
  35%,
  38% {
    transform: rotateZ(3deg) rotateX(12deg) rotateY(20deg);
  }
  55% {
    transform: rotateZ(-3deg) rotateX(-12deg) rotateY(-27deg);
  }
`;

const rubberBand = keyframes`
from {
    transform: scale3d(1, 1, 1);
  }

  30% {
    transform: scale3d(1.25, 0.75, 1);
  }

  40% {
    transform: scale3d(0.75, 1.25, 1);
  }

  50% {
    transform: scale3d(1.15, 0.85, 1);
  }

  65% {
    transform: scale3d(0.95, 1.05, 1);
  }

  75% {
    transform: scale3d(1.05, 0.95, 1);
  }

  to {
    transform: scale3d(1, 1, 1);
  }
`;

// Styled components for cards
const Card = styled.div`
  width: 71.5vw;
  height: 100vw;
  @media screen and (min-width: 600px) {
    width: clamp(12.9vw, 61vh, 18vw);
    height: clamp(18vw, 85vh, 25.2vw);
  }

  position: relative;
  overflow: hidden;
  margin: 20px;
  overflow: hidden;
  z-index: 10;
  touch-action: none;

  border-radius: 5% / 3.5%;
  box-shadow:
    -5px -5px 5px -5px var(--color1),
    5px 5px 5px -5px var(--color2),
    -7px -7px 10px -5px transparent,
    7px 7px 10px -5px transparent,
    0 0 5px 0px rgba(255, 255, 255, 0),
    0 55px 35px -20px rgba(0, 0, 0, 0.5);

  transition:
    transform 0.5s ease,
    box-shadow 0.2s ease;
  will-change: transform, filter;

  background-color: #040712;
  background-image: var(--front);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 50%;
  transform-origin: center;
  cursor: pointer;

  &:hover {
    box-shadow:
      -20px -20px 30px -25px var(--color1),
      20px 20px 30px -25px var(--color2),
      -7px -7px 10px -5px var(--color1),
      7px 7px 10px -5px var(--color2),
      0 0 13px 4px rgba(255, 255, 255, 0.3),
      0 55px 35px -20px rgba(0, 0, 0, 0.5);
  }

  &.charizard {
    --color1: var(--charizard1);
    --color2: var(--charizard2);
    --front: var(--charizardfront);
  }

  &.pika {
    --color1: var(--pika1);
    --color2: var(--pika2);
    --front: var(--pikafront);
  }

  &.eevee {
    --color1: #ec9bb6;
    --color2: #ccac6f;
    --color3: #69e4a5;
    --color4: #8ec5d6;
    --color5: #b98cce;
    --front: var(--eeveefront);
  }

  &:before,
  &:after {
    content: "";
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
    background-repeat: no-repeat;
    opacity: 0.5;
    mix-blend-mode: color-dodge;
    transition: all 0.33s ease;
  }

  &:before {
    background-position: 50% 50%;
    background-size: 300% 300%;
    background-image: linear-gradient(
      115deg,
      transparent 0%,
      var(--color1) 25%,
      transparent 47%,
      transparent 53%,
      var(--color2) 75%,
      transparent 100%
    );
    opacity: 0.5;
    filter: brightness(0.5) contrast(1);
    z-index: 1;
  }

  &:after {
    opacity: 1;
    background-image: url("https://assets.codepen.io/13471/sparkles.gif"),
      url(https://assets.codepen.io/13471/holo.png),
      linear-gradient(
        125deg,
        #ff008450 15%,
        #fca40040 30%,
        #ffff0030 40%,
        #00ff8a20 60%,
        #00cfff40 70%,
        #cc4cfa50 85%
      );
    background-position: 50% 50%;
    background-size: 160%;
    background-blend-mode: overlay;
    z-index: 2;
    filter: brightness(1) contrast(1);
    transition: all 0.33s ease;
    mix-blend-mode: color-dodge;
    opacity: 0.75;
  }

  &.active:after,
  &:hover:after {
    filter: brightness(1) contrast(1);
    opacity: 1;
  }

  &.active,
  &:hover {
    animation: none;
    transition: box-shadow 0.1s ease-out;
  }

  &.active:before,
  &:hover:before {
    animation: none;
    background-image: linear-gradient(
      110deg,
      transparent 25%,
      var(--color1) 48%,
      var(--color2) 52%,
      transparent 75%
    );
    background-position: 50% 50%;
    background-size: 250% 250%;
    opacity: 0.88;
    filter: brightness(0.66) contrast(1.33);
    transition: none;
  }

  &.active:before,
  &:hover:before,
  &.active:after,
  &:hover:after {
    animation: none;
    transition: none;
  }

  &.animated {
    transition: none;
    animation: ${holoCard} 12s ease 0s 1;
    &:before {
      transition: none;
      animation: ${holoGradient} 12s ease 0s 1;
    }
    &:after {
      transition: none;
      animation: ${holoSparkle} 12s ease 0s 1;
    }
  }

  &.clicked {
    transform: scale(1.2); /* Make the clicked card larger */
    box-shadow:
      0 0 30px -5px white,
      0 0 10px -2px white,
      0 55px 35px -20px rgba(0, 0, 0, 0.5); /* Darken the surroundings */
  }

  &.clicked::before {
    /* Adjust these styles as needed */
    background-image: linear-gradient(
      110deg,
      transparent 25%,
      var(--color1) 48%,
      var(--color2) 52%,
      transparent 75%
    );
    background-position: 50% 50%;
    background-size: 250% 250%;
    opacity: 0.88;
    filter: brightness(0.66) contrast(1.33);
  }

  &.clicked::after {
    /* Adjust these styles as needed */
    opacity: 1;
    background-image: url("https://assets.codepen.io/13471/sparkles.gif"),
      url(https://assets.codepen.io/13471/holo.png),
      linear-gradient(
        125deg,
        #ff008450 15%,
        #fca40040 30%,
        #ffff0030 40%,
        #00ff8a20 60%,
        #00cfff40 70%,
        #cc4cfa50 85%
      );
    background-position: 50% 50%;
    background-size: 160%;
    background-blend-mode: overlay;
    z-index: 2;
    filter: brightness(1) contrast(1);
    mix-blend-mode: color-dodge;
    opacity: 0.75;
  }

  &.clicked::before,
  &.clicked::after {
    animation: none;
    transition: none;
  }

  &:hover {
    box-shadow:
      0 0 30px -5px white,
      0 0 10px -2px white,
      0 55px 35px -20px rgba(0, 0, 0, 0.5);
  }
  &:hover:before,
  &.active:before {
    background-image: linear-gradient(
      115deg,
      transparent 20%,
      var(--color1) 36%,
      var(--color2) 43%,
      var(--color3) 50%,
      var(--color4) 57%,
      var(--color5) 64%,
      transparent 80%
    );
  }
  & > span {
    position: relative;
    top: 45%;
  }
`;

const Cards = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  perspective: 2000px;
  position: relative;
  z-index: 1;
  transform: translate3d(0.1px, 0.1px, 0.1px);
  &.card {
    &:nth-child(2) {
      &,
      &:before,
      &:after {
        animation-delay: 0.25s;
      }
    }
    &:nth-child(3) {
      &,
      &:before,
      &:after {
        animation-delay: 0.5s;
      }
    }
    &:nth-child(4) {
      &,
      &:before,
      &:after {
        animation-delay: 0.75s;
      }
    }
  }
`;

const CharizardCard = styled(Card)`
  /* Charizard card specific styles */
  --color1: rgb(0, 231, 255);
  --color2: rgb(255, 0, 231);
  --back: url(https://cdn2.bulbagarden.net/upload/1/17/Cardback.jpg);

  --charizard1: #fac;
  --charizard2: #ddccaa;
  --charizardfront: url(https://cdn2.bulbagarden.net/upload/1/17/Cardback.jpg);
  background-image: var(--charizardfront);
  &.animated {
    transition: none;
    animation: ${holoCard}, ${holoSparkle}, ${holoGradient};
  }
`;

// const PikaCard = styled(Card)`
//   /* Pikachu card specific styles */
// `;

// const EeveeCard = styled(Card)`
//   &:hover {
//     box-shadow:
//       0 0 30px -5px white,
//       0 0 10px -2px white,
//       0 55px 35px -20px rgba(0, 0, 0, 0.5);
//   }
//   &:hover:before,
//   &.active:before {
//     background-image: linear-gradient(
//       115deg,
//       transparent 20%,
//       var(--color1) 36%,
//       var(--color2) 43%,
//       var(--color3) 50%,
//       var(--color4) 57%,
//       var(--color5) 64%,
//       transparent 80%
//     );
//   }
// `;

const PokemonCard: React.FC = () => {
  const [clickedCard, setClickedCard] = useState<number | null>(null);
  const [disableHover, setDisableHover] = useState<boolean | null>(false); // State to disable hover effect
  const [hoverStyle, setHoverStyle] = useState({}); // State to manage hover style

  const handleCardClick = (index: number) => {
    // Card click logic
    if (clickedCard === index) {
      // If the clicked card is already active, reset the state
      setClickedCard(null);
      setDisableHover(false); // Re-enable hover when card is unclicked
    } else {
      setClickedCard(index);
      setDisableHover(true); // Disable hover when card is clicked
    }
  };

  const handleCardHover = (e: React.MouseEvent<HTMLDivElement>) => {
    // Card hover logic
    if (disableHover) {
      return;
    } // If hover is disabled, return

    const style = document.createElement("style");
    document.head.appendChild(style);

    const card = e.currentTarget;
    const pos = [e.nativeEvent.offsetX, e.nativeEvent.offsetY];
    const l = pos[0];
    const t = pos[1];
    const h = card.offsetHeight;
    const w = card.offsetWidth;
    const px = Math.abs(Math.floor((100 / w) * l) - 100);
    const py = Math.abs(Math.floor((100 / h) * t) - 100);
    const pa = 50 - px + (50 - py);
    const lp = 50 + (px - 50) / 1.5;
    const tp = 50 + (py - 50) / 1.5;
    const pxSpark = 50 + (px - 50) / 7;
    const pySpark = 50 + (py - 50) / 7;
    const pOpc = 20 + Math.abs(pa) * 1.5;
    const ty = ((tp - 50) / 2) * -1;
    const tx = ((lp - 50) / 1.5) * 0.5;

    const gradPos = `background-position: ${lp}% ${tp}%;`;
    const sprkPos = `background-position: ${pxSpark}% ${pySpark}%;`;
    const opc = `opacity: ${pOpc / 100};`;
    const transform = { transform: `rotateX(${ty}deg) rotateY(${tx}deg)` };

    style.innerHTML = `
      .${card}:hover::before { ${gradPos} }
      .${Card}:hover::after { ${sprkPos} ${opc} }
    `;

    const newHoverStyle = {
      ...transform,
    };

    setHoverStyle(newHoverStyle); // Update hover style
  };

  const handleCardLeave = () => {
    // Card hover leave logic
    setHoverStyle({}); // Clear hover style
  };

  return (
    <div className="app">
      <GlobalStyle />
      <section className="cards">
        <CharizardCard
          onClick={() => handleCardClick(0)}
          onMouseMove={handleCardHover}
          onMouseLeave={handleCardLeave}
          className={`charizard ${clickedCard === 0 ? "clicked" : ""}`}
          style={disableHover ? {} : hoverStyle}
        ></CharizardCard>
      </section>
    </div>
  );
};

export default PokemonCard;
