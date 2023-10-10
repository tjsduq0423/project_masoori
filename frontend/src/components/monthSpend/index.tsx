import React from "react";
import styled, { keyframes } from "styled-components";

// Define the type for the monthData prop
interface MonthDataItem {
  category: string;
  cost: number;
  analytics: string;
}

interface MonthSpendCarouselProps {
  monthData: MonthDataItem[];
}

// Calculate the animation value outside the template string
const slideAnimation = (slideCount: number) => keyframes`
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(calc(-250px * ${slideCount}));
  }
`;

const SliderContainer = styled.div`
  background: #461356;
  height: 80px;
  margin: auto;
  overflow: hidden;
  position: relative;
  width: 960px;

  &::before,
  &::after {
    content: "";
    height: 100px;
    position: absolute;
    width: 200px;
    z-index: 2;
  }

  &::after {
    right: 0;
    top: 0;
    transform: rotateZ(180deg);
  }

  &::before {
    left: 0;
    top: 0;
  }
`;

const SlideTrack = styled.div<{ slideCount: number }>`
  animation: ${({ slideCount }) => slideAnimation(slideCount)} 10s linear
    infinite;
  display: flex;
  width: ${({ slideCount }) => `calc(250px * ${slideCount})`};
`;

const Slide = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px;
  width: 250px;
  font-size: 24px;
  font-family: "Pyeongchangpeace";
`;

const Cost = styled.p`
  margin-left: 10px;
  font-family: "Pretendard";
  font-size: 26px;
`;

function MonthSpendCarousel({ monthData }: MonthSpendCarouselProps) {
  const slideCount = monthData.length;

  return (
    <SliderContainer>
      <SlideTrack slideCount={slideCount}>
        {monthData.map((item, idx) => (
          <Slide key={idx}>
            {item.category}
            <Cost>{item.cost} ₩</Cost>
          </Slide>
        ))}
      </SlideTrack>
    </SliderContainer>
  );
}

export default MonthSpendCarousel;
