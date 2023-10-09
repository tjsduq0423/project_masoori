import json
import re
import time
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.action_chains import ActionChains

# JSON 파일 경로
json_file_path = "credit_card_processed.json"

# JSON 파일 읽기
with open(json_file_path, "r", encoding="utf-8") as json_file:
    data = json.load(json_file)

    # WebDriver 설정
    webdriver_service = Service((ChromeDriverManager().install()))
    options = Options()
    data_list = []
    driver = webdriver.Chrome(service=webdriver_service, options=options)
    wait = WebDriverWait(driver, 10)

    for card in data["data"]:
        # '카드회사'가 '농협카드'이거나 register_path 가 없을 경우
        if card["카드회사"] == "NH농협카드" or card["register_path"] == "":
            card["register_path"] = ""
            continue
        try:
            # 'register_path'의 'detail' 문자열을 'bridge'로 변경
            access_url = card["register_path"].replace("detail", "bridge")
            print(access_url)
            # 변경된 URL로 Chrome을 사용하여 접속
            driver.get(access_url)

            # URL이 바뀔 때까지 대기 (최대 10초 동안)
            WebDriverWait(driver, 20).until(EC.url_changes(access_url))

            # 바뀐 URL 가져오기
            updated_register_path = driver.current_url
            print(updated_register_path)

            card["register_path"] = updated_register_path
        except Exception as e:
            print(e)
            card["register_path"] = ""
            continue
    # Chrome 브라우저 종료
    driver.quit()

    # 카드 정보 리스트가 업데이트된 상태로 저장됩니다.
    with open("updated_credit_card.json", "w", encoding="utf-8") as file:
        json.dump(data, file, ensure_ascii=False)
