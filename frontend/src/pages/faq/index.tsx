import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown } from "@fortawesome/free-solid-svg-icons";
import styled from "styled-components"; // Import styled-components
import { useFaqData } from "@/apis/menu/Queries/useFaqData";

interface FaqItemProps {
  active: boolean;
}

interface FAQItem {
  title: string;
  content: string;
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
  margin-top: 100px;
  margin-bottom: 80px;
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
    color: ${(props) => (props.active ? "#4A3246" : "#5E3A66")};
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

const FaqPage = () => {
  const faqData: FAQItem[] = useFaqData().faqList;

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
          {faqData.map((faq, idx) => (
            <FaqItem
              key={idx}
              active={activeItems[idx]}
              onClick={() => toggleItem(idx)}
            >
              <h3 className="faq-title">{faq.title}</h3>
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
