import styled from "styled-components";
import {
  FacebookShareButton,
  FacebookIcon,
  TwitterIcon,
  TwitterShareButton,
} from "react-share";
import { CopyToClipboard } from "react-copy-to-clipboard";
// import { useScript } from "@/shareHook/hook";
import { useEffect } from "react";
import shareKakaoIcon from "@/assets/img/shareIcon/shareKakao.webp";
import { useRecoilValue } from "recoil";
import { specialImageUrlState } from "../../states/dictionaryState";

// 제목과 버튼을 감싸는 컨테이너
const FlexContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

// 버튼을 배치시키는 컨테이너
const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 48px);
  grid-column-gap: 8px;
  justify-content: center;
  align-items: center;
  margin-bottom: 16px;
`;

declare global {
  interface Window {
    Kakao: any; // 또는 Kakao SDK의 타입 정의를 참조해야 합니다.
  }
}

const URLShareButton = styled.button`
  width: 48px;
  height: 48px;
  color: white;
  border-radius: 24px;
  border: 0px;
  font-weight: 800;
  font-size: 18px;
  cursor: pointer;
  background-color: #7362ff;
  &:hover {
    background-color: #a99fee;
  }
`;

const ShareModal = () => {
  const { Kakao } = window;
  const realUrl = "https://masoori.site/";
  const currentUrl = window.location.href;
  const specialImageUrl = useRecoilValue(specialImageUrlState);

  // 재랜더링시에 실행되게 해준다.
  useEffect(() => {
    // init 해주기 전에 clean up 을 해준다.
    Kakao.cleanup();
    // 자신의 js 키를 넣어준다.
    Kakao.init("4ae4faeac2156b8d5592a875fa6888aa");
    // 잘 적용되면 true 를 뱉는다.
    console.log(Kakao.isInitialized());
  }, [Kakao]);

  const shareKakao = () => {
    Kakao.Share.sendDefault({
      objectType: "feed",
      content: {
        title: "소비패턴 길잡이, 마수리",
        description: "마녀가 직접 봐주는 나의 소비패턴 타로카드!",
        imageUrl: specialImageUrl,
        link: {
          webUrl: specialImageUrl,
        },
      },
      buttons: [
        {
          title: "마녀에게 테스트 받으러 가기",
          link: {
            webUrl: specialImageUrl,
          },
        },
      ],
    });
  };

  const KakaoShareButton = styled.a`
    cursor: pointer;
  `;

  const KakaoIcon = styled.img`
    width: 48px;
    height: 48px;
    border-radius: 24px;
  `;

  return (
    <FlexContainer>
      <h1>공유하기</h1>
      <GridContainer>
        <FacebookShareButton url={currentUrl}>
          <FacebookIcon size={48} round={true} borderRadius={24}></FacebookIcon>
        </FacebookShareButton>
        <TwitterShareButton url={currentUrl}>
          <TwitterIcon size={48} round={true} borderRadius={24}></TwitterIcon>
        </TwitterShareButton>
        <CopyToClipboard text={currentUrl}>
          <URLShareButton>URL</URLShareButton>
        </CopyToClipboard>
        <KakaoShareButton
          onClick={() => {
            shareKakao();
          }}
        >
          <KakaoIcon src={shareKakaoIcon}></KakaoIcon>
        </KakaoShareButton>
      </GridContainer>
    </FlexContainer>
  );
};

export default ShareModal;
