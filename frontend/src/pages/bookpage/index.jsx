import React, { useState, useEffect, useRef } from "react";
import HTMLFlipBook from "react-pageflip";
import "./style.css";

const PageCover = React.forwardRef((props, ref) => {
  return (
    <div className="page page-cover" ref={ref} data-density="hard">
      <div className="page-content">
        <h2>{props.children}</h2>
      </div>
    </div>
  );
});

const Page = React.forwardRef((props, ref) => {
  return (
    <div className="page" ref={ref}>
      <div className="page-content">
        <h2 className="page-header">Page header - {props.number}</h2>
        <div className="page-image"></div>
        <div className="page-text">{props.children}</div>
        <div className="page-footer">{props.number + 1}</div>
      </div>
    </div>
  );
});

const DemoBook = () => {
  const [page, setPage] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [state, setState] = useState(""); // 상태값 초기화
  const [orientation, setOrientation] = useState(""); // 방향값 초기화
  const flipBookRef = useRef();

  const nextButtonClick = () => {
    flipBookRef.current.getPageFlip().flipNext();
  };

  const prevButtonClick = () => {
    flipBookRef.current.getPageFlip().flipPrev();
  };

  const onPage = (e) => {
    setPage(e.data);

    // 상태값 및 방향값 업데이트 예시 (실제로는 필요한 로직에 따라 업데이트)
    setState("Some state value");
    setOrientation("Landscape"); // 또는 'Portrait' 등으로 설정
  };

  useEffect(() => {
    try {
      if (flipBookRef.current) {
        const pageCount = flipBookRef.current.getPageFlip().getPageCount();
        setTotalPage(pageCount);
      }
    } catch (error) {
      console.error("Error accessing getPageFlip:", error);
    }
  }, []);

  return (
    <div>
      {/* HTMLFlipBook 컴포넌트 설정 */}
      <HTMLFlipBook
        width={550}
        height={733}
        size="stretch"
        minWidth={315}
        maxWidth={1000}
        minHeight={400}
        maxHeight={1533}
        maxShadowOpacity={0.5}
        showCover={true}
        mobileScrollSupport={true}
        onFlip={onPage}
        className="demo-book"
        ref={flipBookRef}
      >
        <PageCover>BOOK TITLE</PageCover>
        <Page number={1}>Lorem ipsum...</Page>
        <Page number={2}>Lorem ipsum...</Page>
        <Page number={3}>Lorem ipsum...</Page>
        <Page number={4}>Lorem ipsum...</Page>
        <Page number={5}>Lorem ipsum...</Page>
        {/* ... */}
        <PageCover>THE END</PageCover>
      </HTMLFlipBook>
    </div>
  );
};

export default DemoBook;
