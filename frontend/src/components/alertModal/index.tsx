import { useNavigate } from "react-router-dom";
import styled from "styled-components";

export interface StyledAlertModalProps {
  width: string;
  imageUrl: string;
  topText: string;
  middleText: string;
  bottomText: string;
  topTextColor: string;
  middleTextColor: string;
  bottomTextColor: string;
  upperSectionBackground: string;
  lowerSectionBackground: string;
  topTextFontSize: string;
  middleTextFontSize: string;
  bottomTextFontSize: string;
  topTextPaddingTopBottom: string;
  middleTextPaddingTopBottom: string;
  middleTextPaddingLeftRight: string;
  topTextFontWeight: string;
  middleTextFontWeight: string;
  bottomTextFontWeight: string;
  zIndex?: string;
  routerLink: string;
}

const StyledAlertModal = styled.div<StyledAlertModalProps>`
  width: ${(props) => props.width};
  border-radius: 30px;
  background: transparent; // 배경색을 투명으로 설정
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px;
  z-index: ${(props) => props.zIndex};
`;

const UpperSection = styled.div<{ upperSectionBackground: string }>`
  background: ${(props) => props.upperSectionBackground}; // 첫 번째 구역 배경색
  border-radius: 30px 30px 0px 0px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
`;

const LowerSection = styled.div<{ lowerSectionBackground: string }>`
  background: ${(props) => props.lowerSectionBackground}; // 두 번째 구역 배경색
  border-radius: 0px 0px 30px 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-left: 50px;
  padding-right: 50px;
`;

const ImageContainer = styled.div`
  width: 50%;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 30px;
`;

const Image = styled.img`
  max-width: 100%;
  max-height: 100%;
`;

const TopText = styled.div<{
  topTextColor: string;
  topTextFontSize: string;
  topTextPaddingTopBottom: string;
  topTextFontWeight: string;
}>`
  color: ${(props) => props.topTextColor};
  font-size: ${(props) => props.topTextFontSize};
  padding-top: ${(props) => props.topTextPaddingTopBottom};
  padding-bottom: ${(props) => props.topTextPaddingTopBottom};
  font-weight: ${(props) => props.topTextFontWeight};
`;

const MiddleText = styled.div<{
  middleTextColor: string;
  middleTextFontSize: string;
  middleTextPaddingTopBottom: string;
  middleTextPaddingLeftRight: string;
  middleTextFontWeight: string;
}>`
  color: ${(props) => props.middleTextColor};
  font-size: ${(props) => props.middleTextFontSize};
  font-weight: ${(props) => props.middleTextFontWeight};
  padding-top: ${(props) => props.middleTextPaddingTopBottom};
  padding-bottom: ${(props) => props.middleTextPaddingTopBottom};
  padding-left: ${(props) => props.middleTextPaddingLeftRight};
  padding-right: ${(props) => props.middleTextPaddingLeftRight};
`;

const BottomText = styled.div<{
  bottomTextColor: string;
  bottomTextFontSize: string;
  bottomTextFontWeight: string;
}>`
  color: ${(props) => props.bottomTextColor};
  font-size: ${(props) => props.bottomTextFontSize};
  padding-top: 25px;
  padding-bottom: 25px;
  font-weight: ${(props) => props.bottomTextFontWeight};
  padding-right: 7px;
`;

const AlertModal = ({
  width,
  topText,
  middleText,
  bottomText,
  imageUrl,
  topTextColor,
  middleTextColor,
  bottomTextColor,
  upperSectionBackground,
  lowerSectionBackground,
  topTextFontSize,
  middleTextFontSize,
  bottomTextFontSize,
  topTextPaddingTopBottom,
  middleTextPaddingTopBottom,
  middleTextPaddingLeftRight,
  topTextFontWeight,
  middleTextFontWeight,
  bottomTextFontWeight,
  zIndex,
  routerLink,
}: StyledAlertModalProps) => {
  // const navigate = useNavigate();

  const handleLowerSectionClick = () => {
    window.location.href = routerLink;
  };

  const content = { __html: middleText };

  return (
    <StyledAlertModal
      width={width}
      topText={topText}
      middleText={middleText}
      bottomText={bottomText}
      imageUrl={imageUrl}
      topTextColor={topTextColor}
      middleTextColor={middleTextColor}
      bottomTextColor={bottomTextColor}
      upperSectionBackground={upperSectionBackground}
      lowerSectionBackground={lowerSectionBackground}
      topTextFontSize={topTextFontSize}
      middleTextFontSize={middleTextFontSize}
      bottomTextFontSize={bottomTextFontSize}
      topTextPaddingTopBottom={topTextPaddingTopBottom}
      middleTextPaddingTopBottom={middleTextPaddingTopBottom}
      middleTextPaddingLeftRight={middleTextPaddingLeftRight}
      topTextFontWeight={topTextFontWeight}
      middleTextFontWeight={middleTextFontWeight}
      bottomTextFontWeight={bottomTextFontWeight}
      zIndex={zIndex}
      routerLink={routerLink}
    >
      <UpperSection upperSectionBackground={upperSectionBackground}>
        <ImageContainer>
          <Image src={imageUrl} alt="Image" />
        </ImageContainer>
        <TopText
          topTextColor={topTextColor}
          topTextFontSize={topTextFontSize}
          topTextPaddingTopBottom={topTextPaddingTopBottom}
          topTextFontWeight={topTextFontWeight}
        >
          {topText}
        </TopText>
        <MiddleText
          middleTextColor={middleTextColor}
          middleTextFontSize={middleTextFontSize}
          middleTextPaddingTopBottom={middleTextPaddingTopBottom}
          middleTextPaddingLeftRight={middleTextPaddingLeftRight}
          middleTextFontWeight={middleTextFontWeight}
        >
          <div dangerouslySetInnerHTML={content} />
        </MiddleText>
      </UpperSection>
      <LowerSection
        lowerSectionBackground={lowerSectionBackground}
        onClick={handleLowerSectionClick}
      >
        <BottomText
          bottomTextColor={bottomTextColor}
          bottomTextFontSize={bottomTextFontSize}
          bottomTextFontWeight={bottomTextFontWeight}
        >
          {bottomText}
        </BottomText>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="15"
          height="18"
          viewBox="0 0 17 28"
          fill="none"
        >
          <path
            d="M0 2.968L11.0528 14L0 25.032L2.9736 28L17 14L2.9736 0L0 2.968Z"
            fill="#EAE2ED"
          />
        </svg>
      </LowerSection>
    </StyledAlertModal>
  );
};

export default AlertModal;
