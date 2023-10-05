import os
import json

def CardDataSplit():
    try:
        file_path = f"{os.path.dirname(os.path.realpath(__file__))}/CardList"
        jsonData = json.load(open(f"{os.path.dirname(os.path.realpath(__file__))}/json/creditCardList.json", encoding='UTF-8'))
        for card in jsonData:
            fileName = ""
            cardInfo = ""
            for data in jsonData[card]:
                temp = jsonData[card]
                #카드 아이디
                if data == 'id':
                    fileName += f"card{temp[data]}"
                    cardInfo += f"카드ID : {temp[data]}\n"
                #카드 이름
                elif data == 'name':
                    cardInfo += f"카드명 : {temp[data]}\n"
                #카드 회사
                elif data == 'company':
                    cardInfo += f"카드사 : {temp[data]}\n"
                #국내 연회비 
                elif data == 'domestic':
                    domestic = 0
                    if temp[data] != "":
                        domestic = temp[data]
                    cardInfo += f"국내 연회비 : {domestic}원\n"
                #해외 연회비
                elif data == 'overseas':
                    overseas = 0
                    if temp[data] != "":
                        overseas = temp[data]
                    cardInfo += f"해외 연회비 : {overseas}원\n"
                #전월실적
                elif data == 'condition':
                    modified = temp[data].replace("전월실적", "").strip()
                    if modified == "없음":
                        modified = "0원"
                    cardInfo += f"전월실적 : {modified}\n"
                #브랜드
                elif data == 'brandList':
                    cardInfo += f"브랜드 : "
                    for brand in temp[data]:
                        cardInfo += f"{brand}, "
                    cardInfo = cardInfo[:len(cardInfo)-2]
                    cardInfo += f"\n"
                #혜택
                elif data == 'benefitList':
                    cardInfo += f"혜택 : \n"
                    for benefit in temp[data]:
                        cardInfo += f"{benefit['title']} : {benefit['description']}\n"
                        # cardInfo += f"\t{benefit['detailDescription']}\n"
            if not os.path.exists(file_path):
                os.makedirs(file_path)
            with open(f"{file_path}/{fileName}.txt", 'w', encoding='UTF-8') as file:
                file.write(cardInfo)
    except Exception as e:
        print(e)   