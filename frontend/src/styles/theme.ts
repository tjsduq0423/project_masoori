import { DefaultTheme } from "styled-components";

const color = {
  // color
  white: "white",

  titleBeige: "#FDF1F1",
  mainBackgroundPurple: "#4D3548",
  basicBtnPurple: "#CAB2D3",
  basicBtnTextPurple: "#813E83",
  tarotCardBtnPurple: "#5E3A66",
  puzzleModalLightPurple: "#EAE2ED",
  loadingBlack: "#202020",
  collectionTextBrown: "#3E0F0E",
  collectionRed: "#901818",
  collectionDatePink: "#D39090",
  profileGraphPurple: "#7C5874",
  profileDetailTextPurple: "#DACBD7",
  profileBasicPurple: "#A37C9B",
  profleHoverPurple: "#95648B",
  FAQTextPurple: "#4A3246",
  FAQBoxLightPurple: "#E9D5E5",
  FAQArrowPurpleGray: "#D0BDCC",
  recommendDatePurple: "#957796",
  recommendLinePurple: "#813E83",
  introBackgroundPurple: "#4C3447",

  // opacity 포함된 color

  introBubblePurple: "rgba(77, 27, 69, 0.50)",
  navagationBubblePurple: "rgba(110, 39, 98, 0.50)",
  BasicBubbleBrown: "rgba(77, 27, 45, 0.50)",
  challengeBubblePink: "rgba(176, 60, 101, 0.50)",
  hashtagBubbleBrown: "rgba(47, 6, 6, 0.60)",
} as const;

const theme: DefaultTheme = {
  color,
};

export default theme;
