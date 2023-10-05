# 식품 : 일반음식점, 식품자동판매기업, 휴게음식점, 위탁급식영업
# 교통 : 석유판매업 
# 광고 : 옥외광고업
# 건강 : 건강기능식품일반판매업
# 통신 : SKT, LG, KT
# 문화 : 노래연습장업, 공연장, 영화상영업, 영화상영관, 한옥체험업, 지방문화원, 박물관, 미술관, 일반게임제공업, 청소년게임제공업, 목욕장업, 유원시설업(기타), 썰매장업, 일반유원시설업, 빙상장업, 종합휴양업, 문화예술법인
# 의료 : 의료기기판매(임대)업, 의원, 약국, 의료기기수리업, 의료법인, 병원, 산후조리업, 부속의료기관
# 동물 : 동물용의료용구판매업, 동물약국, 동물판매업, 동물병원, 동물용의약품도매상
# 여행 : 숙박업, 일반여행업, 관광숙박업, 국내여행업, 국외여행업, 일반야영장업, 국내여행업, 종합여행업, 자동차야영장업
# 미용 : 미용업
# 운동 : 체육도장업, 체력단련장업, 골프연습장업, 종합체육시설업, 수영장업, 당구장업, 무도학원업, 등록체육시설업
# 종교 : 전통사찰
# 의류 : 세탁업
# 마트 : 식품판매업(기타), 식품소분업
# PC방 : 복합유통게임제공업
# 이발 : 이용업
# 기타 : 안경업, 담배도매업

# 토스
# 식비, 쇼핑, 이체, 편의점-마트-잡화, 술-유흥, 카페-간식, 카테고리 없음, 미용, 여행-숙박, 교육, 주거-통신, 교통-자동차, 의료-건강-피트니스, 생활

# 네이버
# 쇼핑, 식비, 카페-간식, 생활, 교육, 이체, ATM출금, 뷰티-미용, 술-유흥, 위미-여가, 주거-통신, 의료-건강-피트니스

# 뱅크샐러드
# 식사, 미분류, 카페/간식, 생활, 의복/미용, 여행/숙박, 교육, 술/유흥, 경조사, 주거/통신, 교통, 의료/건강


# 식비, 편의점/마트, 카페/간식, 여행/숙박, 교육, 주거/통신, 교통, 의료/건강, 문화/여가, 온라인 쇼핑, 배달 음식, 반려동물, 기타(미분류, 카테고리 없음)

# 식비 : 일반음식점, 식품자동판매기업, 위탁급식영업
# 편의점/마트 : 식품판매업(기타), 식품소분업, 담배소매업, 대규모점포, {}
# 카페/간식 : 휴게음식점, 제과점영업, 즉석판매제조가공업, {}
# 여행/숙박 : 종합휴양업, 숙박업, 일반여행업, 관광숙박업, 국내여행업, 국외여행업, 일반야영장업, 국내여행업, 종합여행업, 자동차야영장업
# 교육 : 
# 주거/통신 : {}
# 생활 : 미용업, 세탁업, 이용업, 안경업, 목욕장업
# 교통 : 석유판매업, {}
# 의료/건강 : 건강기능식품일반판매업, 의료기기판매(임대)업, 의원, 약국, 의료기기수리업, 의료법인, 병원, 산후조리업, 부속의료기관, 
# 운동 : 체육도장업, 체력단련장업, 종합체육시설업, 골프연습장업, 수영장업, 무도학원업, 등록체육시설업
# 문화/여가 : 노래연습장업, 공연장, 영화상영업, 영화상영관, 한옥체험업, 지방문화원, 박물관, 미술관, 일반게임제공업, 청소년게임제공업,  유원시설업(기타), 썰매장업, 일반유원시설업, 빙상장업, 당구장업, 전통사찰, 복합유통게임제공업
# 온라인 쇼핑 : {}
# 배달 음식 : {}
# 반려동물 : 동물용의료용구판매업, 동물약국, 동물판매업, 동물병원, 동물용의약품도매상
# 기타

import json
import os
import csv

def CsvToJson():
    try:
        # 카테고리에 들어가는 전체 데이터를 넣을 딕셔너리
        data = {}
        # 대전시 등록업소 개방서비스명을 카테고리에 매칭
        category = []
        words = {}
        with open(f'{os.path.dirname(os.path.realpath(__file__))}/csv/categories.csv', newline='', encoding='UTF-8-sig') as csvfile:
            csvreader = csv.reader(csvfile)
            for row in csvreader:
                key = row[0]
                category.append(key)
                if words.get(key) is None:
                    words[key] = []
                for i in range(1, len(row)-1):
                    if row[i] != '':
                        words[key].append(row[i])
        if words.get('') is not None:
            del words['']
        # print(words)
        # print(category)

        # 빈 카테고리 딕셔너리 생성
        for cat in category:
            if data.get(cat) is None:
                data[cat] = []

        # print(data)

        with open(f'{os.path.dirname(os.path.realpath(__file__))}/csv/customName.csv', newline='', encoding='UTF-8-sig') as csvfile:
            csvreader = csv.reader(csvfile)
            for row in csvreader:
                key = row[0]
                for i in range(1, len(row)-1):
                    if row[i] != '':
                        data[key].append(row[i])
                
        # print(data)

        # words의 value(카테고리 key)만 모으기
        keyList = []
        for key in words:
            for value in words[key]:
                keyList.append(value)

        # print(keyList)

        # key가 keyList에 포함되는 애들만 추출
        businessName = {}
        with open(f'{os.path.dirname(os.path.realpath(__file__))}/csv/data.csv', newline='') as csvfile:
            csvreader = csv.reader(csvfile)
            for row in csvreader:
                key = row[3]
                value = row[4]
                if key in keyList:
                    if businessName.get(key) is None:
                        businessName[key] = []
                    businessName[key].append(value)

        # 대전광역시 등록업소 이름을 data의 value에 넣기
        for businessNameKey in businessName:
            # words의 value를 하나씩 돌면서 value가 같은 경우의 key값에 등록업소명 추가
            for value in businessName[businessNameKey]:
                for key in words:
                    if businessNameKey in words[key]:
                        data[key].append(value)

        # restaurant 추가 데이터
        restaurant = {}
        with open(f'{os.path.dirname(os.path.realpath(__file__))}/csv/restaurant.csv', newline='') as csvfile:
            csvreader = csv.reader(csvfile)
            for row in csvreader:
                key = row[2]
                value = row[0]
                if key in keyList:
                    if restaurant.get(key) is None:
                        restaurant[key] = []
                    restaurant[key].append(value)

        # restaurant 데이터 data에 추가
        for restaurantKey in restaurant:
            for value in restaurant[restaurantKey]:
                for key in words:
                    if restaurantKey in words[key]:
                        data[key].append(value)
        file_path = f"{os.path.dirname(os.path.realpath(__file__))}/json"
        if not os.path.exists(file_path):
                os.makedirs(file_path)
        with open(f"{file_path}/categoryData.json", 'w', encoding='UTF-8') as file:
            json.dump(data, file, ensure_ascii=False)
        print("categoryData.json 생성 완료")
    except Exception as e:
        print(e)