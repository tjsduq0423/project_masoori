import ToggleSwitch from "@/components/toggle";

const TestPage = () => {
  return (
    <div>
      <ToggleSwitch
        textOn="카카오 연동 시작하기"
        textOff="카카오 연동 취소하기"
        backgroundImage="https://www.svgrepo.com/show/368252/kakao.svg"
        backgroundColor="#ffe617"
      />
    </div>
  );
};

export default TestPage;
