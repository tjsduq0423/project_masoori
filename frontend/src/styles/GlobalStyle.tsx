import { createGlobalStyle } from "styled-components";
import "swiper/swiper-bundle.min.css";

const GlobalStyle = createGlobalStyle`
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        /* color: ${({ theme }) => theme.color.white}; */
    }

    ul, ol, li {
        list-style: none;
    }

    body {
        font-family: "Pretendard";
        overflow: auto;
    }

    .scrollable {
        overflow: auto;
        &::-webkit-scrollbar {
            display: none;
        }
    }
`;

export default GlobalStyle;
