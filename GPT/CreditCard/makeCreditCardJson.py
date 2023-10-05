#카드 데이터 가져오기
import json
import requests
import os

def MakeCreditCardJson():
    try:
        temp = {}
        login = requests.post("https://masoori.site/api/user/login", json={'email':'ssafy@gmail.com', 'password':'1Q2w3e4r!'})
        token = login.json()['accessToken']

        for i in range(1072, 2609):
            response = requests.get(f"https://masoori.site/api/creditcard/{i}", headers={"Authorization" : "Bearer "+token} , timeout=0.5)
            if response.status_code == 200:
                temp[f'card{i}'] = response.json()
        file_path = f"{os.path.dirname(os.path.realpath(__file__))}/json"
        if not os.path.exists(file_path):
            os.makedirs(file_path)
        with open(f"{file_path}/creditCardList.json", 'w', encoding='UTF-8') as file:
            json.dump(temp, file, ensure_ascii=False)
        print("creditCard 데이터 로딩 완료")
    except Exception as e:
        print(e)