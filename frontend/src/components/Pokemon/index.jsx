import { useState } from "react";
import styles from "./styles.module.css";
import { styled } from "styled-components";

const StyledTarotCardTop = styled.div`
  width: ${(props) => props.cardWidth};
  height: auto;
  position: absolute;
  z-index: 1;
`;

const TopImage = styled.img`
  width: ${(props) => props.cardWidth};
  height: auto;
`;

const BottomText = styled.div`
  position: absolute;
  width: 100%;
  bottom: ${(props) => props.bottom};
  left: 50%;
  transform: translateX(-50%);
  z-index: 1;
  font-size: ${(props) => props.fontsize};
  color: #5e3a66;
  font-family: "Museum";
`;

const PokemonCard = ({
  imageUrl,
  cardWidth,
  cardSrc,
  text,
  fontsize,
  bottom = "5px",
}) => {
  const [clickedCard, setClickedCard] = useState(null);
  const [disableHover, setDisableHover] = useState(false); // State to disable hover effect
  const [hoverStyle, setHoverStyle] = useState({}); // State to manage hover style

  const handleCardHover = (e) => {
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
    .${styles.card}:hover::before { ${gradPos} }
    .${styles.card}:hover::after { ${sprkPos} ${opc} }
  `;

    const newHoverStyle = {
      ...transform,
    };

    setHoverStyle(newHoverStyle); // Update hover style
  };

  const handleCardLeave = () => {
    setHoverStyle({}); // Clear hover style
  };

  return (
    <div className={styles.app}>
      <section className={styles.cards}>
        <div
          className={`${styles.card} ${styles.charizard} ${
            clickedCard === 0 ? styles.clicked : ""
          }`}
          onMouseMove={handleCardHover}
          onMouseLeave={handleCardLeave}
          style={{
            backgroundImage: `url(${imageUrl})`, // Set background image to the passed imageUrl
            ...(disableHover ? {} : hoverStyle), // Apply hover style only if not disabled
            // ... Other styles ...
          }}
        >
          <StyledTarotCardTop cardWidth={cardWidth}>
            <TopImage cardWidth={cardWidth} src={cardSrc}></TopImage>
            <BottomText text={text} fontsize={fontsize} bottom={bottom}>
              {text}
            </BottomText>
          </StyledTarotCardTop>
        </div>
        {/* Other card divs with similar event handlers */}
      </section>
    </div>
  );
};

export default PokemonCard;
