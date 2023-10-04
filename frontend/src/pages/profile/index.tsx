import styled from "styled-components"; // Import styled-components
import ToggleSwitch from "@/components/toggle";
import DonutChart from "@/components/donutGraph";
import cardFrontImage from "../../assets/img/cardFront.png";
import { usePostGeneration } from "@/apis/user/Mutations/usePostGeneration";
import { usePostAlram } from "@/apis/user/Mutations/usePostAlram";
import SettingFinancialGoals from "@/components/settingGoals";
import { useRecoilState } from "recoil";
import { settingModalOpenState } from "@/states/spendState";
import { getUserInfo } from "@/apis/menu/menuAPI";
import { useState, useEffect } from "react";
import TarotCard from "@/components/tarotCard";

import tarotCardFront from "@/assets/img/tarotCard/tarotCardFront.png";

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
  //마이페이지 소비카드 생성 연동 변경 API 시작

  const ChangeGeneration = usePostGeneration();

  const handleChangeGeneration = async () => {
    try {
      const result = await ChangeGeneration.mutateAsync();
      console.log(result);
    } catch (error) {
      console.error("소비카드 생성 연동 변경에 실패했습니다.", error);
    }
  };

  //마이페이지 소비카드 생성 연동 변경 API 종료

  //마이페이지 sms 알림 연동 변경 API 시작

  const ChangeAlram = usePostAlram();

  const handleChangeAlram = async () => {
    try {
      const result = await ChangeAlram.mutateAsync();
      console.log(result);
    } catch (error) {
      console.error("sms 알림 연동 변경에 실패했습니다.", error);
    }
  };

  //마이페이지 sms 알림 연동 변경 API 종료

  interface UserData {
    imagePath?: string | undefined;
    smsAlarm?: boolean;
    cardGeneration?: boolean;
    dailySpending?: number;
    weeklySpending?: number;
    monthlySpending?: number;
    monthlySpendingGoal?: number;
    isAuthenticated?: boolean;
  }

  const [userInfo, setUserInfo] = useState<UserData>({
    imagePath: "",
    smsAlarm: false,
    cardGeneration: false,
    dailySpending: 0,
    weeklySpending: 0,
    monthlySpending: 0,
    monthlySpendingGoal: 0,
    isAuthenticated: false,
  });

  const [isSettingOpen, setIsSettingOpen] = useRecoilState(
    settingModalOpenState
  );

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userData = await getUserInfo();
        setUserInfo({
          imagePath: userData.imagePath ? userData.imagePath : "",
          smsAlarm: userData.smsAlarm ? userData.smsAlarm : false,
          cardGeneration: userData.cardGeneration
            ? userData.cardGeneration
            : false,
          dailySpending: userData.dailySpending ? userData.dailySpending : 0,
          weeklySpending: userData.weeklySpending ? userData.weeklySpending : 0,
          monthlySpending: userData.monthlySpending
            ? userData.monthlySpending
            : 0,
          monthlySpendingGoal: userData.monthlySpendingGoal
            ? userData.monthlySpendingGoal
            : 0,
          isAuthenticated: userData.isAuthenticated
            ? userData.isAuthenticated
            : false,
        });
      } catch (error) {
        console.error("데이터 가져오기 실패:", error);
      }
    };

    fetchData();
  }, [isSettingOpen]);

  console.log(userInfo);

  return (
    <Container>
      <WeatherSide>
        <TarotCard
          width="347px"
          height="500px"
          cardWidth="100%"
          cardSrc={tarotCardFront}
          imageSrc={userInfo.imagePath || ""}
          bottomImageWidth="100%"
          text="내 프로필 사진"
          fontsize="20px"
          bottom="1.2rem"
        ></TarotCard>
      </WeatherSide>
      <InfoSide>
        <TodayInfo>
          <InfoTitle>Wallets</InfoTitle>
        </TodayInfo>
        <WeekContainer
          onClick={() => {
            setIsSettingOpen(!isSettingOpen);
          }}
        >
          <DonutChart
            value={
              (userInfo.dailySpending! / (userInfo.monthlySpendingGoal! / 30)) *
              100
            }
            valuelabel="Day"
            size={115}
            strokewidth={15}
          />
          <DonutChart
            value={
              (userInfo.weeklySpending! / (userInfo.monthlySpendingGoal! / 4)) *
              100
            }
            valuelabel="WEEK"
            size={115}
            strokewidth={15}
          />
          <DonutChart
            value={
              (userInfo.monthlySpending! / userInfo.monthlySpendingGoal!) * 100
            }
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
            onClick={() => {
              handleChangeAlram();
            }}
          >
            <p
              style={{ marginRight: "38px", fontSize: "20px", fontWeight: 700 }}
            >
              CARD
            </p>
            <ToggleSwitch
              textOn="문자 연동 취소하기"
              textOff="문자 연동 등록하기"
              backgroundImage="https://www.svgrepo.com/show/368252/kakao.svg"
              backgroundColor="#e0cf19"
              checked={userInfo.smsAlarm!}
            />
          </div>
          <div
            style={{ display: "flex", alignItems: "center" }}
            onClick={() => {
              handleChangeGeneration();
            }}
          >
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
              checked={userInfo.cardGeneration!}
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
      {isSettingOpen ? (
        <SettingFinancialGoals
          monthlySpendingGoal={userInfo.monthlySpendingGoal!}
        />
      ) : (
        <></>
      )}
    </Container>
  );
};

export default ProfilePage;
