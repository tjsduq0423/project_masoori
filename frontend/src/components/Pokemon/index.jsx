import { useState } from "react";
import styles from "./styles.module.css";

const PokemonCard = () => {
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
          style={disableHover ? {} : hoverStyle} // Apply hover style only if not disabled
        ></div>
        {/* Other card divs with similar event handlers */}
      </section>
    </div>
  );
};

export default PokemonCard;
