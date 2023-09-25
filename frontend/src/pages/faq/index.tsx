import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown } from "@fortawesome/free-solid-svg-icons";
import styled from "styled-components"; // Import styled-components

interface FaqItemProps {
  active: boolean;
}

const PageContainer = styled.div`
  display: flex;
  justify-content: center;
  min-height: 100vh; /* Use min-height to ensure it covers the entire viewport height */
  background-color: #a37c9b;
`;

const TitleText = styled.div`
  color: #fdf1f1;
  font-family: "Pyeongchangpeace";
  font-size: 50px;
  margin: 50px;
`;

const FaqContainer = styled.div`
  max-width: 1000px;
  margin: 0 auto;
  border-radius: 10px;
  background-color: #fff;
  overflow: hidden;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
`;

const FaqItem = styled.div<FaqItemProps>`
  box-sizing: border-box;
  text-align: left;
  background: transparent;
  padding: 60px;
  position: relative;
  overflow: hidden;

  // Conditional styles based on the active prop
  background-color: ${(props) => (props.active ? "#f8f8f8" : "transparent")};
  box-shadow: ${(props) =>
    props.active ? "inset 4px 0px 0px 0px #4D3548" : "none"};

  &:not(:first-child) {
    border-top: 1px solid #e6e6e6;
  }

  .faq-title {
    font-weight: 700;
    font-size: 20px;
    color: ${(props) => (props.active ? "#4A3246" : "initial")};
  }

  .faq-text {
    margin: 30px 0 0;
    display: ${(props) => (props.active ? "block" : "none")};
    line-height: 1.5rem;
  }
`;

const FaqToggle = styled.button<FaqItemProps>`
  background-color: transparent;
  border: 1px solid #e6e6e6;
  color: inherit;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  padding-top: 3px;
  position: absolute;
  top: 50px;
  right: 40px;
  height: 40px;
  width: 40px;
  transition: 0.3s ease;
  outline: none;

  // Conditional styles based on the active prop
  transform: ${(props) => (props.active ? "rotate(180deg)" : "none")};
  background-color: ${(props) => (props.active ? "#D0BDCC" : "transparent")};
  border-color: ${(props) => (props.active ? "#D0BDCC" : "#e6e6e6")};
  color: ${(props) => (props.active ? "#fff" : "inherit")};
`;

const FaqPage: React.FC = () => {
  const [faqList] = useState([
    {
      articleNo: "1",
      subject: "주제1",
      content: "ㄴ미ㅏㅓㅁ니아ㅓ니",
    },
    {
      articleNo: "2",
      subject: "삼성 청년 SW 아카데미에는 어떤 사람들이 지원하면 좋을까요?",
      content:
        "만 29세 이하의 청년 취업 준비생이라면 누구나 본 과정에 지원할 수 있습니다.- 국내외 4년제 대학 졸업자(학사이상), 전공무관 ※ 졸업자 및 2023년 8월 졸업예정자삼성 청년 SW 아카데미가 희망하는 인재상은 논리적 사고력, 열정, 학습의지를 갖춘 사람입니다. SW를 잘 이해하고 개발하기 위해서는 논리적이고 창의적으로 사고하는 것이 필요합니다. 또한 SW를 학습하고자 하는 열정, 프로젝트 수행에 필요한 협업 능력 등도 중요합니다.교육생 선발도 이러한 내용을 바탕으로 진행됩니다.",
    },
    {
      articleNo: "3",
      subject: "주제1",
      content: "ㄴ미ㅏㅓㅁ니아ㅓ니",
    },
    {
      articleNo: "4",
      subject: "주제1",
      content: "ㄴ미ㅏㅓㅁ니아ㅓ니",
    },
  ]);
  const [activeItems, setActiveItems] = useState([false, false, false, false]);

  const toggleItem = (idx: number) => {
    const updatedActiveItems = [...activeItems];
    updatedActiveItems[idx] = !updatedActiveItems[idx];
    setActiveItems(updatedActiveItems);
  };

  return (
    <PageContainer>
      <div className="container">
        <TitleText>FAQ</TitleText>
        <FaqContainer>
          {faqList.map((faq, idx) => (
            <FaqItem
              key={faq.articleNo}
              active={activeItems[idx]}
              onClick={() => toggleItem(idx)}
            >
              <h3 className="faq-title">{faq.subject}</h3>
              <p className="faq-text">{faq.content}</p>
              <FaqToggle active={activeItems[idx]}>
                <FontAwesomeIcon icon={faAngleDown} />
              </FaqToggle>
            </FaqItem>
          ))}
        </FaqContainer>
      </div>
    </PageContainer>
  );
};

export default FaqPage;
