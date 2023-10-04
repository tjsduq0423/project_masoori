import { createGlobalStyle } from "styled-components";
import "swiper/swiper-bundle.min.css";

const GlobalStyle = createGlobalStyle`
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        /* color: ${({ theme }) => theme.color.white}; */
        &::-webkit-scrollbar-track
        {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
            border-radius: 10px;
            background-color: #D0BDCC;
        }

        &::-webkit-scrollbar
        {
            width: 8px;
            background-color: #D0BDCC;
        }

        &::-webkit-scrollbar-thumb
        {
            border-radius: 10px;
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
            background-color: #813E83;
        }
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

    
    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-moz-keyframes fadeIn { /* Firefox */
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-webkit-keyframes fadeIn { /* Safari and Chrome */
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }   

    @keyframes fadeOut {
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
    @-moz-keyframes fadeOut { /* Firefox */
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
    @-webkit-keyframes fadeOut { /* Safari and Chrome */
        from {
            opacity: 1;
        }
        to {
            opacity: 0;
        }
    }
`;

export default GlobalStyle;
