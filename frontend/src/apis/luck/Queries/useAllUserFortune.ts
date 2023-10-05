import { useQuery } from "@tanstack/react-query";
import { getAllUserFortune } from "@/apis/luck/luckAPI";

const useAllUserFortune = () => {
  // useQuery에 적절한 의존성 배열을 추가하여 필요한 조건에서만 요청을 다시 보내도록 설정
  const { data } = useQuery(["allUserFortune"], () => getAllUserFortune(), {
    // staleTime을 설정하여 캐시가 업데이트될 때까지 요청을 보내지 않음
    staleTime: 60000, // 예: 60초 동안 캐시를 사용하고, 그 이후에 요청을 다시 보냄 (원하는 시간으로 조절)
  });

  return data;
};

export { useAllUserFortune };
