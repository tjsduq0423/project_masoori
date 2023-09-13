import React, { useRef, useState } from "react";
import HTMLFlipBook from "react-pageflip";
import styled from "styled-components";

// 이미지 파일을 require를 사용하여 가져옴

// card Text 관련 이미지
const basicCard: string = require("../../assets/img/basicCard.png");
const specialCard: string = require("../../assets/img/specialCard.png");
const luckyCard: string = require("../../assets/img/luckyCard.png");

// card Image 관련
const basicCardImage: string = require("../../assets/img/basicCardImage.png");
const specialCardImage: string = require("../../assets/img/specialCardImage.png");
const luckyCardImage: string = require("../../assets/img/luckyCardImage.png");

const PageContainer = styled.div`
  padding: 30px;
  background-color: #fdfaf7;
  color: #785e3a;
  border: 1px solid #c2b5a3;
  overflow: hidden;

  &.--left {
    border-right: 0;
    box-shadow: inset -7px 0 30px -7px rgba(0, 0, 0, 0.4);
  }

  &.--right {
    border-left: 0;
    box-shadow: inset 7px 0 30px -7px rgba(0, 0, 0, 0.4);

    .page-footer {
      text-align: right;
    }
  }

  &.hard {
    background-color: hsl(35, 50, 90);
    border: solid 1px hsl(35, 20, 50);
  }

  &.page-cover {
    background-color: hsl(35, 45, 80);
    color: hsl(35, 35, 35);
    border: solid 1px hsl(35, 20, 50);

    h2 {
      text-align: center;
      padding-top: 50%;
      font-size: 210%;
    }

    &.page-cover-top {
      box-shadow:
        inset 0px 0 30px 0px rgba(36, 10, 3, 0.5),
        -2px 0 5px 2px rgba(0, 0, 0, 0.4);
    }

    &.page-cover-bottom {
      box-shadow:
        inset 0px 0 30px 0px rgba(36, 10, 3, 0.5),
        10px 0 8px 0px rgba(0, 0, 0, 0.4);
    }
  }
`;

const PageContent = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
  color: #901818;
`;

const PageImage = styled.div`
  height: 100%;
  background-size: contain;
  background-position: center center;
  background-repeat: no-repeat;
  background-image: url("../../assets/img/luckyCard.png"); /* Replace with your image path */
`;

const PageText = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  flex-grow: 1;
  font-size: 80%;
  text-align: justify;
  margin-top: 10px;
  padding-top: 10px;
  box-sizing: border-box;
  border-top: solid 1px hsl(35, 55, 90);
`;

interface PageProps {
  imageUrl: string;
  children: React.ReactNode;
  number?: number;
}

const Page = React.forwardRef<HTMLDivElement, PageProps>((props, ref) => {
  const pageImageStyle: React.CSSProperties = {
    backgroundImage: `url(${props.imageUrl})`,
  };

  return (
    <PageContainer ref={ref}>
      <PageContent>
        <PageImage style={pageImageStyle}></PageImage>
        <PageText>{props.children}</PageText>
      </PageContent>
    </PageContainer>
  );
});

const ImagePage = React.forwardRef<HTMLDivElement, { imageUrl: string }>(
  (props, ref) => {
    const pageImageStyle: React.CSSProperties = {
      backgroundImage: `url(${props.imageUrl})`,
    };

    return (
      <PageContainer ref={ref}>
        <PageContent>
          <PageImage style={pageImageStyle}></PageImage>
        </PageContent>
      </PageContainer>
    );
  }
);

const Book = () => {
  const flipBookRef = useRef<any | null>(null); // HTMLFlipBook 타입을 any로 설정
  const [currentPage, setCurrentPage] = useState<number>(0);

  const handlePageFlip = (e: { data: number }) => {
    try {
      setCurrentPage(e.data);
    } catch (error) {
      console.error("An error occurred while handling page flip:", error);
    }
  };

  return (
    <div>
      <HTMLFlipBook
        width={302}
        height={425}
        maxShadowOpacity={0.5}
        mobileScrollSupport={true}
        className="demo-book"
        ref={flipBookRef}
        onFlip={handlePageFlip}
        // 아래 부분에서 누락된 프로퍼티들을 추가합니다.
        style={{ perspective: "2000px" }}
        startPage={0}
        size="fixed"
        minWidth={302}
        minHeight={425}
        maxWidth={1000}
        maxHeight={1414}
        showCover={false}
        drawShadow={true}
        flippingTime={1000}
        usePortrait={false}
        startZIndex={1}
        autoSize={true}
        showPageCorners={true}
        clickEventForward={true}
        swipeDistance={10}
        useMouseEvents={true}
        disableFlipByClick={false}
      >
        <Page number={1} imageUrl={basicCard}>
          지금까지 네가 모아온 카드들을 볼 수 있어. 혹시 아니? 카드가 많아지면
          여기에 신비한 힘이 깃들 수도 있고... 무려 요정이 그려준 카드니 어쩌면
          허황된 이야기가 아닐지도 모른단다.
        </Page>
        <ImagePage imageUrl={basicCardImage}></ImagePage>
        <Page number={3} imageUrl={specialCard}>
          여긴 챌린지를 달성해야만 얻을 수 있는 카드들을 모아둔 곳이란다.
          조각들을 모두 모으고 나면, 별빛이 담긴 카드를 얻게 될거야. 정말
          기대되지 않니?
        </Page>
        <ImagePage imageUrl={specialCardImage}></ImagePage>
        <Page number={5} imageUrl={luckyCard}>
          혹시 오늘의 금전운은 봤니? 운이 좋다면 매일 다른 카드가 나와서 금세
          채울 수도 있고, 아니면 같은 카드만 나오겠지? 이 곳을 가득 채우는 것
          또한 너의 운에 맡겨져 있겠구나.
        </Page>
        <ImagePage imageUrl={luckyCardImage}></ImagePage>
      </HTMLFlipBook>
    </div>
  );
};

export default Book;
