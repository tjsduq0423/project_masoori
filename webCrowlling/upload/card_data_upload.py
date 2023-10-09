import json
import mariadb

# JSON 파일 경로
json_file_path = "img_processed_credit.json"

# 데이터베이스 연결 설정
db_host = "masoori.site"
db_port = 5555
db_user = "ssafy_b308"
db_password = "ssafy_b308_1"
db_name = "masoori"

# JSON 파일 읽기
with open(json_file_path, "r", encoding="utf-8") as json_file:
    data = json.load(json_file)
    card_info_list = data["data"]

# 데이터베이스 연결
try:
    connection = mariadb.connect(
        user=db_user, password=db_password, host=db_host, port=db_port, database=db_name
    )
    cursor = connection.cursor()

    # 여기에서 데이터베이스 작업을 수행할 수 있습니다.
    # 데이터베이스 생성
    create_db_sql = f"CREATE DATABASE IF NOT EXISTS {db_name} CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
    cursor.execute(create_db_sql)

    # 데이터베이스 선택
    cursor.execute(f"USE {db_name};")

    # credit_card 테이블에 데이터 삽입
    for card_info in card_info_list:
        card_name = card_info.get("카드이름", "")
        company = card_info.get("카드회사", "")

        # 연회비 정보가 없는 경우 기본적으로 빈 딕셔너리 반환
        fee_info = card_info.get("연회비", {})
        domestic = fee_info.get("국내전용", "")
        overseas = fee_info.get("해외겸용", "")

        condition = card_info.get("전월실적조건", "")
        # brand = ",".join(card_info.get("브랜드", []))
        image_path = card_info.get("img_path", "")
        img_attr = card_info.get("img_attr", "")
        register_path = card_info.get("register_path", "")

        # INSERT 문 생성 및 실행
        insert_sql = (
            "INSERT INTO credit_card (name, company, domestic, overseas, conditions, image_path, img_attr, register_path) "
            "VALUES (%s, %s, %s, %s, %s,%s, %s, %s)"
        )
        cursor.execute(
            insert_sql,
            (
                card_name,
                company,
                domestic,
                overseas,
                condition,
                image_path,
                img_attr,
                register_path,
            ),
        )

        # 마지막으로 추가한 레코드의 credit_card_id 가져오기
        last_insert_id = cursor.lastrowid

        # 브랜드 테이블에 데이터 삽입
        brands = card_info.get("브랜드", "")
        for brand in brands:
            # INSERT문 생성 및 실행
            insert_brand_sql = (
                "INSERT INTO credit_card_brands (credit_card_credit_card_id, brands) "
                "VALUES (%s, %s)"
            )
            cursor.execute(
                insert_brand_sql,
                (last_insert_id, brand),
            )

        # 주요혜택들을 benefit 테이블에 삽입
        benefits = card_info["주요혜택들"]
        for benefit in benefits:
            title = benefit["title"]
            description = benefit["desc"]
            detail_description = benefit["detail_desc"]
            # INSERT문 생성 및 실행
            insert_benefit_sql = (
                "INSERT INTO benefits (credit_card_id, title, description, detail_description) "
                "VALUES (%s, %s, %s, %s)"
            )
            cursor.execute(
                insert_benefit_sql,
                (last_insert_id, title, description, detail_description),
            )
    # 변경 사항을 커밋
    connection.commit()

except mariadb.Error as e:
    print(f"Mariadb 데이터베이스 연결 오류: {e}")

finally:
    cursor.close()
    connection.close()
    print("MariaDB 데이터베이스 연결이 닫혔습니다.")
