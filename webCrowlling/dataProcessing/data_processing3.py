import json
import re

# JSON 파일 경로
json_file_path = "updated_credit_card.json"
# JSON 파일 읽기
with open(json_file_path, "r", encoding="utf-8") as json_file:
    data = json.load(json_file)

    # "register_path": "https://www.card-gorilla.com/home"인 데이터 제거
    filtered_data = [
        card
        for card in data["data"]
        if card["register_path"] != "https://www.card-gorilla.com/home"
    ]

    # 결과를 새 JSON 파일에 저장
    with open("filtered_credit_card.json", "w", encoding="utf-8") as json_file:
        json.dump({"data": filtered_data}, json_file, ensure_ascii=False, indent=4)

    # # 카드 정보 리스트가 업데이트된 상태로 저장됩니다.
    # with open("check_card_processed.json", "w", encoding="utf-8") as file:
    #     json.dump(data, file, ensure_ascii=False)
