import re

def ContentSearch(description):
    try:
        # 정규 표현식을 사용하여 정보 추출
        most_frequent_pattern = re.compile(r'가장 자주 결제한 카테고리([^\n]+)')
        most_expensive_pattern = re.compile(r'가장 많이 결제한 카테고리([^\n]+)')

        most_frequent_match = most_frequent_pattern.search(description)
        most_expensive_match = most_expensive_pattern.search(description)

        result = {}
        if most_frequent_match:
            temp = most_frequent_match.group(1).replace(":", "").replace(" ", "").split(",")[0].split("(")
            keyword = temp[0]
            frequency = temp[1].replace(")", "")
            result['frequencyKeyword'] = keyword
            result['frequency'] = frequency
        if most_expensive_match:
            temp = most_expensive_match.group(1).replace(":", "").split(", ")[0].replace(" ", "").split("(")
            keyword = temp[0]
            amount = temp[1].replace(",", "").replace("원", "").replace(")", "")
            result['amountKeyword'] = keyword
            result['amount'] = amount
        return result
    except Exception as e:
        print(e)