import React, { useEffect } from "react";
import styled from "styled-components";
import Swiper, { Navigation, Pagination } from "swiper"; // Import Swiper and necessary modules
import RegistBtn from "@/components/registbtn";

import foodIcon from "@/assets/img/foodIcon.png";
import moneyIcon from "@/assets/img/moneyIcon.png";

const StyledMain = styled.main`
  position: relative;
  min-height: 100vh;
  column-gap: 3rem;
  padding-block: min(20vh, 3rem);
  padding-inline: 2.3em;
  align-items: center;
  justify-content: center;
  background: #461356;
  overflow: hidden;

  @media screen and (min-width: 960px) {
    display: flex;
    padding-inline: 0;
  }
`;

const StyledContent = styled.div`
  width: 100%;
  color: #fff;
  position: relative;
  z-index: 2;
  margin-bottom: 3em;

  @media screen and (min-width: 960px) {
    width: 40%;
    padding-left: 5em;
    margin-bottom: 0;
  }
`;

const StyledTitle = styled.h2`
  font-family: "Pyeongchangpeace";
  text-align: left;
  letter-spacing: 0.8px;
  font-size: 2.8rem;
  line-height: 1.2;
`;

const StyledDate = styled.p`
  font-family: "Pyeongchangpeace";
  text-align: left;
  color: #957796;
  letter-spacing: 0.8px;
  font-size: 1.5rem;
  line-height: 2;
`;

const StyledCardName = styled.div`
  font-size: 2rem;
  text-align: left;
  line-height: 1;
  width: fit-content;
  margin-bottom: 15px;
`;

const CardNameText = styled.div`
  background: linear-gradient(to top, #813e83 100%, transparent 50%);
  font-weight: 700;
`;

const StyledCardCompany = styled.p`
  text-align: left;
  line-height: 1;
  margin-bottom: 30px;
`;

const StyledCardCondition = styled.p`
  font-size: 1.5rem;
  text-align: left;
  line-height: 1;
  margin-bottom: 30px;
`;

const StyledCardBenefits = styled.p`
  font-size: 1.3rem;
  text-align: left;
  line-height: 1;
`;

const StyledCardAlarm = styled.p`
  font-size: 1rem;
  text-align: left;
  line-height: 1.8;
  margin-top: 10px;
  margin-bottom: 30px;
`;

const StyledSwiperContainer = styled.div`
  position: relative;
  overflow: hidden;
  width: 100%;
  right: 0px;
  margin: 0 auto;

  @media screen and (min-width: 960px) {
    width: 60%;
    right: -60px;
  }
`;

const StyledSwiper = styled.div`
  position: relative;
  width: 100%;
  z-index: 2;
`;

const StyledSwiperSlide = styled.div`
  width: 10rem;
  height: 24rem;
  display: flex;
  flex-direction: column;
  align-items: self-start;
  position: relative;
  border-radius: 12px;
  box-shadow: -1px 5px 15px #0000001f;
  text-align: center;
  opacity: 0.4;
  transition: opacity 0.4s ease-in;

  span {
    display: inline-block;
    background: #9926e1;
    border-radius: 0 50px 50px 0;
    text-transform: capitalize;
    padding: 12px 20px;
    letter-spacing: 0.5px;
    font-weight: 500;
    position: absolute;
    top: 2em;
    left: 0;
    color: #fff;
  }

  h3 {
    color: #fff;
    font-size: 1.3rem;
    line-height: 1.4;
    margin-bottom: 0.625rem;
    letter-spacing: 0.8px;
    position: relative;
    word-wrap: break-word;
  }

  @media screen and (min-width: 800px) {
    h3 {
      font-size: 1.8rem;
    }
  }

  p {
    color: #fff;
    line-height: 1.6;
    font-size: 0.8rem;
  }

  .slide-content {
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translate(-50%, -10px);
    width: 90%;
  }

  &.swiper-slide--one {
    background: #0f2027;
    background:
      linear-gradient(to bottom, #2c536400, #203a4303, #0f2027cc),
      url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
        no-repeat 50% 50% / cover;
  }

  &.swiper-slide--one h3 {
    font-family: "Courgette", cursive;
    font-weight: 300;
  }

  &.swiper-slide--two {
    background:
      linear-gradient(to bottom, #2c536400, #203a4303, #0f2027cc),
      url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
        no-repeat 50% 50% / cover;
  }

  &.swiper-slide--two h3 {
    font-family: "Noto Serif Vithkuqi", serif;
    font-weight: 300;
  }

  &.swiper-slide--three {
    background: url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
      no-repeat 50% 50% / cover;
  }

  &.swiper-slide--four {
    background: url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
      no-repeat 50% 50% / cover;
  }

  &.swiper-slide--five {
    background: url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
      no-repeat 50% 50% / cover;
  }

  &.swiper-slide--six {
    background:
      linear-gradient(to bottom, #2c536400, #203a4303, #0f2027cc),
      url("https://api.card-gorilla.com:8080/storage/card/87/card_img/20239/87card.png")
        no-repeat 50% 50% / cover;
  }

  &.swiper-slide--six h3 {
    font-family: "Chonburi", cursive;
    font-weight: 400;
  }

  &.swiper-slide-active {
    display: grid;
    opacity: 1;
  }
`;

const StyledSwiperPagination = styled.div`
  position: relative;
  bottom: -0.313rem;
  text-align: center;
  margin-top: 35px;
  width: auto;

  .swiper-pagination-bullet {
    border-radius: 0;
    width: 1.5rem;
    height: 0.25rem;
    background: #fff;
  }

  .swiper-pagination-bullet-active {
    background: #fff;
  }
`;

const StyledCircle = styled.div`
  position: absolute;
  bottom: -5em;
  left: -8em;
  width: clamp(150px, 40vw, 400px);
  height: clamp(150px, 40vw, 400px);
  background: black;
  border-radius: 50%;
  z-index: 1;
  opacity: 0.7;
`;

const StyledWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 40px;
`;

const StyledItem = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "Pyeongchangpeace";
  text-align: left;
  font-size: 1.5rem;
`;

const StyledIcon = styled.img`
  margin-right: 20px;
`;

const CardRecommend = () => {
  const data = [
    {
      name: "string",
      company: "string",
      domestic: "string",
      overseas: "string",
      condition: "string",
      brandList: ["string"],
      imagePath:
        "https://images.unsplash.com/photo-1500258593672-b080c40f4b02?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
      registerPath: "string",
      reason: "string",
      benefitList: [
        {
          title: "string",
          description: "string",
          detailDescription: "string",
        },
      ],
    },
    {
      name: "string",
      company: "string",
      domestic: "string",
      overseas: "string",
      condition: "string",
      brandList: ["string"],
      imagePath:
        "https://images.unsplash.com/photo-1500258593672-b080c40f4b02?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80",
      registerPath: "string",
      reason: "string",
      benefitList: [
        {
          title: "string",
          description: "string",
          detailDescription: "string",
        },
      ],
    },
  ];
  Swiper.use([Navigation, Pagination]); // Initialize Swiper with necessary modules

  useEffect(() => {
    const swiper = new Swiper(".swiper", {
      effect: "coverflow",
      grabCursor: true,
      spaceBetween: 30,
      centeredSlides: false,
      coverflowEffect: {
        rotate: 0,
        stretch: 0,
        depth: 0,
        modifier: 1,
        slideShadows: false,
      },
      loop: true,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },
      keyboard: {
        enabled: true,
      },
      mousewheel: {
        thresholdDelta: 70,
      },
      breakpoints: {
        460: {
          slidesPerView: 3,
        },
        768: {
          slidesPerView: 3,
        },
        1024: {
          slidesPerView: 3,
        },
        1600: {
          slidesPerView: 3.6,
        },
      },
    });
  }, []);

  return (
    <StyledMain>
      <StyledContent>
        <StyledDate>23.09</StyledDate>
        <StyledTitle>이달의 추천카드</StyledTitle>
        <StyledWrapper>
          <StyledItem>
            <StyledIcon src={foodIcon} alt="Background" />
            음식
          </StyledItem>
          <StyledItem>
            <StyledIcon src={moneyIcon} alt="Background" />
            323,000
          </StyledItem>
        </StyledWrapper>
        <StyledCardName>
          <CardNameText>KB 국민 My WE:SH 카드</CardNameText>
        </StyledCardName>
        <StyledCardCompany>카드사: KB 국민 카드</StyledCardCompany>
        <StyledCardCondition>
          연회비 : xxxxxx원 <br />
          전월 실적 조건 : xxxxxx원
        </StyledCardCondition>
        <StyledCardBenefits>
          혜택 <br />
          [나한테 진심 서비스] KB Pay 10% 할인
        </StyledCardBenefits>
        <StyledCardAlarm>
          * 자세한 사항은 사이트에서 확인해주세요.
        </StyledCardAlarm>
        <RegistBtn />
      </StyledContent>
      <StyledSwiperContainer>
        <StyledSwiper className="swiper">
          <div className="swiper-wrapper">
            <StyledSwiperSlide className="swiper-slide swiper-slide--one">
              <span>bestseller</span>
            </StyledSwiperSlide>
            <StyledSwiperSlide className="swiper-slide swiper-slide--two">
              <span>bestseller</span>
            </StyledSwiperSlide>
            <StyledSwiperSlide className="swiper-slide swiper-slide--three">
              <span>bestseller</span>
            </StyledSwiperSlide>
            <StyledSwiperSlide className="swiper-slide swiper-slide--four">
              <span>bestseller</span>
            </StyledSwiperSlide>
            <StyledSwiperSlide className="swiper-slide swiper-slide--five">
              <span>bestseller</span>
            </StyledSwiperSlide>
            <StyledSwiperSlide className="swiper-slide swiper-slide--six">
              <span>bestseller</span>
            </StyledSwiperSlide>
          </div>
        </StyledSwiper>
        <StyledSwiperPagination className="swiper-pagination"></StyledSwiperPagination>
      </StyledSwiperContainer>
      <StyledCircle></StyledCircle>
    </StyledMain>
  );
};

export default CardRecommend;
