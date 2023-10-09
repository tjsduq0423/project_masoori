import json
import re

# JSON 파일 경로
json_file_path = "_credit_card.json"
# JSON 파일 읽기
with open(json_file_path, "r", encoding="utf-8") as json_file:
    data = json.load(json_file)
    card_info_list = data["data"]
    for card_info in card_info_list:
        연회비_원본 = card_info["연회비"]
        연회비_목록 = re.findall(r"(국내전용|해외겸용)\s*(없음|[\d,]+원)", 연회비_원본)
        연회비_dict = {}  # 연회비 정보를 담을 딕셔너리 초기화

        for key, value in 연회비_목록:
            if value != "없음":
                value = int(value.replace(",", "").replace("원", ""))
            연회비_dict[key] = value
        card_info["연회비"] = 연회비_dict  # 가공된 연회비 정보로 업데이트

        # 브랜드 가공
        if "브랜드" in card_info:
            브랜드_원본 = card_info["브랜드"]
            브랜드_목록 = []  # 브랜드 정보를 담을 리스트 초기화

            if "AMEX" in 브랜드_원본:
                브랜드_목록.append("AMEX")
            if "VISA" in 브랜드_원본:
                브랜드_목록.append("VISA")
            if "mastercard" in 브랜드_원본:
                브랜드_목록.append("mastercard")
            card_info["브랜드"] = 브랜드_목록  # 가공된 브랜드 정보로 업데이트

    # 카드 정보 리스트가 업데이트된 상태로 저장됩니다.
    with open("credit_card_processed.json", "w", encoding="utf-8") as file:
        json.dump(data, file, ensure_ascii=False)
