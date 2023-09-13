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
}

const StyledAlertModal = styled.div<StyledAlertModalProps>`
  width: ${(props) => props.width};
  border-radius: 30px;
  background: transparent; // 배경색을 투명으로 설정
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px;
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
  width: 500px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 30px;
`;

const Image = styled.img`
  max-width: 80%;
  max-height: 80%;
`;

const TopText = styled.div<{
  topTextColor: string;
  topTextFontSize: string;
  topTextPaddingTopBottom: string;
}>`
  color: ${(props) => props.topTextColor};
  font-size: ${(props) => props.topTextFontSize};
  padding-top: ${(props) => props.topTextPaddingTopBottom};
  padding-bottom: ${(props) => props.topTextPaddingTopBottom};
`;

const MiddleText = styled.div<{
  middleTextColor: string;
  middleTextFontSize: string;
  middleTextPaddingTopBottom: string;
}>`
  color: ${(props) => props.middleTextColor};
  font-size: ${(props) => props.middleTextFontSize};
  padding-top: ${(props) => props.middleTextPaddingTopBottom};
  padding-bottom: ${(props) => props.middleTextPaddingTopBottom};
`;

const BottomText = styled.div<{
  bottomTextColor: string;
  bottomTextFontSize: string;
}>`
  color: ${(props) => props.bottomTextColor};
  font-size: ${(props) => props.bottomTextFontSize};
  padding-top: 25px;
  padding-bottom: 25px;
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
}: StyledAlertModalProps) => {
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
    >
      <UpperSection upperSectionBackground={upperSectionBackground}>
        <ImageContainer>
          <Image src={imageUrl} alt="Image" />
        </ImageContainer>
        <TopText
          topTextColor={topTextColor}
          topTextFontSize={topTextFontSize}
          topTextPaddingTopBottom={topTextPaddingTopBottom}
        >
          {topText}
        </TopText>
        <MiddleText
          middleTextColor={middleTextColor}
          middleTextFontSize={middleTextFontSize}
          middleTextPaddingTopBottom={middleTextPaddingTopBottom}
        >
          {middleText}
        </MiddleText>
      </UpperSection>
      <LowerSection lowerSectionBackground={lowerSectionBackground}>
        <BottomText
          bottomTextColor={bottomTextColor}
          bottomTextFontSize={bottomTextFontSize}
        >
          {bottomText}
        </BottomText>
      </LowerSection>
    </StyledAlertModal>
  );
};

export default AlertModal;
