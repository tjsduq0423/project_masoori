import pika
import pydantic
from fastapi import FastAPI

app = FastAPI()
# TODO:  연결 서버 도메인 설정(localhost x), callback 함수에서 langchian에 데이터 넘기고 처리 결과 json으로 파싱하기, 파싱한 데이터 다시 message로 pub해서 spirng server로 전달하기


# 서버 테스트용 root 요청 API
@app.get("/")
def read_root():
    return {"Hello": "World"}


# rabbitMQ 변수 정리
rabbit_mq_server_domain = "j9b308.p.ssafy.io"
pub_queue_name = "recommend.res"
sub_queue_name = "recommend.req"
# RabbitMQ 연결 설정
credentials = pika.PlainCredentials(username="admin", password="masoori")
connection = pika.BlockingConnection(
    pika.ConnectionParameters(rabbit_mq_server_domain, credentials=credentials)
)
channel = connection.channel()

# pub 를 위한 queue 선언
channel.queue_declare(queue=pub_queue_name, durable=True)


# sub 에서 메시지를 받아 처리하는 함수
def callback(ch, method, properties, body):
    # 메시지 처리 로직 작성 - LangChain 모듈 import -> 작성 필수
    # body가 spring boot 서버에서 받은 데이터임
    print(f"Received {body}")

    # 다시 처리 완료 된 값을 메시지로 파싱 - class 형태 -> json.dump 사용하여 json으로 변환 후 직렬화해서 전달
    # import json
    # data = {  # 예시
    #     'name': 'John',
    #     'age': 30,
    #     'city': 'New York'
    # }
    # response = json.dumps(data)
    response = "New message to publish"

    # 파싱된 메시지 큐로 전달
    ch.basic_publish(exchange="", routing_key=pub_queue_name, body=response)

    # 메시지 처리 완료 시 MQ에 처리 했다고 전달하는 함수
    ch.basic_ack(delivery_tag=method.delivery_tag)


# sub 설정
channel.basic_qos(prefetch_count=1)
channel.basic_consume(
    queue=sub_queue_name, on_message_callback=callback, auto_ack=False
)
channel.start_consuming()
