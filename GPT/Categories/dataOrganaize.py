#json 데이터로 임베딩용 txt파일 만들기
import json
import os

def SaveData(fileName, fileData):
    try:
        file_path = f"{os.path.dirname(os.path.realpath(__file__))}/txt"
        if not os.path.exists(file_path):
            os.makedirs(file_path)
        with open(f"{file_path}/{fileName}.txt", 'w', encoding='UTF-8') as file:
            file.write(fileData)
    except Exception as e:
        print(e)
def DataOrganaize():
    try:
        jsonData = json.load(open(f"{os.path.dirname(os.path.realpath(__file__))}/json/categoryData.json", encoding='UTF-8'))
        for category in jsonData:
            fileName = category
            if "/" in category:
                fileName = category.replace("/", ".").strip()
            value = ""
            for data in jsonData[category]:
                temp = data.replace(" ", "").strip()
                if len(temp) >= 3:
                    value += f"{temp}\n"
            SaveData(fileName, value)
    except Exception as e:
        print(e)