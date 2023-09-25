import styled from "styled-components"; // Import styled-components
import ToggleSwitch from "@/components/toggle";
import DonutChart from "@/components/donutGraph";
import cardFrontImage from "../../assets/img/cardFront.png";

const Container = styled.div`
  border-radius: 25px;
  -webkit-box-shadow: 0 0 70px -10px rgba(0, 0, 0, 0.2);
  box-shadow: 0 0 70px -10px rgba(0, 0, 0, 0.2);
  background-color: #a37c9b;
  color: #ffffff;
  height: 520px;
  width: 800px;
`;

const WeekContainer = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 25px;
`;

const FooterText = styled.p`
  color: rgba(255, 255, 255, 0.6);
  font-size: 14px;
  font-style: normal;
  font-weight: 700;
`;

const WeatherSide = styled.div`
  position: relative;
  height: 100%;
  border-radius: 25px;
  background: url(${cardFrontImage}) no-repeat;
  background-size: cover;
  width: 345px;
  -webkit-box-shadow: 0 0 20px -10px rgba(0, 0, 0, 0.2);
  box-shadow: 0 0 20px -10px rgba(0, 0, 0, 0.2);
  -webkit-transition: -webkit-transform 300ms ease;
  transition: -webkit-transform 300ms ease;
  -o-transition: transform 300ms ease;
  transition: transform 300ms ease;
  transition:
    transform 300ms ease,
    -webkit-transform 300ms ease;
  -webkit-transform: translateZ(0) scale(1.02) perspective(1000px);
  transform: translateZ(0) scale(1.02) perspective(1000px);
  float: left;
`;

const WeatherGradient = styled.div`
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  border-radius: 25px;
  opacity: 0.8;
`;

const InfoSide = styled.div`
  position: relative;
  float: left;
  height: 100%;
  padding-top: 25px;
  width: 56.8%;
`;

const TodayInfo = styled.div`
  padding: 15px;
  border-radius: 10px;
`;

const InfoTitle = styled.div`
  color: #fff;
  font-family: "Pyeongchangpeace";
  font-size: 32px;
  font-weight: 700;
`;

const LocationContainer = styled.div`
  padding: 25px 35px;
`;

// Define other styled-components similarly for the remaining classes

const ProfilePage = () => {
  return (
    <Container>
      <WeatherSide>
        <WeatherGradient />
      </WeatherSide>
      <InfoSide>
        <TodayInfo>
          <InfoTitle>Wallets</InfoTitle>
        </TodayInfo>
        <WeekContainer>
          <DonutChart value={75} valuelabel="Day" size={115} strokewidth={15} />
          <DonutChart
            value={75}
            valuelabel="WEEK"
            size={115}
            strokewidth={15}
          />
          <DonutChart
            value={75}
            valuelabel="MONTH"
            size={115}
            strokewidth={15}
          />
        </WeekContainer>
        <LocationContainer>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              marginBottom: "10px",
            }}
          >
            <p
              style={{ marginRight: "38px", fontSize: "20px", fontWeight: 700 }}
            >
              CARD
            </p>
            <ToggleSwitch
              textOn="카카오 연동 취소하기"
              textOff="카카오 연동 등록하기"
              backgroundImage="https://www.svgrepo.com/show/368252/kakao.svg"
              backgroundColor="#e0cf19"
            />
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <p
              style={{ marginRight: "30px", fontSize: "20px", fontWeight: 700 }}
            >
              ALERT
            </p>
            <ToggleSwitch
              textOn="카드 연동 취소하기"
              textOff="카드 연동 등록하기"
              backgroundImage="https://assets.codepen.io/4175254/boo-face.png"
              backgroundColor="#FFF"
            />
          </div>
        </LocationContainer>
        <div>
          <FooterText>
            ※ 사용 카드와 연동하면 매주 분석 결과 타로가 자동으로 생성됩니다.
          </FooterText>
          <FooterText>
            ※ 카카오 계정을 연동하면 매주 분석 결과를 알림으로 보내드립니다.
          </FooterText>
        </div>
      </InfoSide>
    </Container>
  );
};

export default ProfilePage;
