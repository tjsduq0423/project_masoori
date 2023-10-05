import pika
import json
import asyncio
from pydantic import BaseModel
from typing import List

import os
os.environ['OPENAI_API_KEY'] = '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'

from Categories.useFaiss import FaissCategorization

from Description.summarizeSpend import SummarizeSpend
from Description.contentSearch import ContentSearch
from Description.makeCardName import MakeCardName

from CreditCard.recomandCreditCard import RecommandCreditCard
from CreditCard.creditCardSearch import CreditCardSearch

# rabbitMQ 변수 정리
rabbit_mq_server_domain_name = "j9b308.p.ssafy.io"
rabbit_mq_server_domain_port = 5672
pub_queue_name = "analytics.res"
sub_queue_name = "analytics.req"

# RabbitMQ 연결 설정
credentials = pika.PlainCredentials(username="admin", password="masoori")
connection = pika.BlockingConnection(
    pika.ConnectionParameters(
        host=rabbit_mq_server_domain_name,
        credentials=credentials,
        port=rabbit_mq_server_domain_port,
    )
)

channel = connection.channel()

# pub 를 위한 queue 선언
channel.queue_declare(queue=pub_queue_name, durable=True)


class Transaction(BaseModel):
    date: str
    content: str
    amount: int
    dealPlaceName: str


class RecommendedCreditCard(BaseModel):
    creditCardId: int
    reason: str


# reqDto
class AnalyticsRequestMessage(BaseModel):
    userId: int
    userWeeklyTransactionList: List[Transaction]


# resDto 선언
class MonthlySpendingAndCreditcard(BaseModel):
    userId: int
    creditCardList: List[RecommendedCreditCard]


# sub 에서 메시지를 받아 처리하는 함수
def callback(ch, method, properties, body):
    try:
        # 이거 쓰면댐 -> Anal
        request_message_dict = json.loads(body)
        # 서비스 로직 실행 -> 결과값 res 객체에 넣고 쏘면 댐.

        userId = request_message_dict['userId']
        spendList = request_message_dict['userWeeklyTransactionList']

        categorization = FaissCategorization(spendList)
        description = SummarizeSpend(categorization)
        summary = ContentSearch(description)
        recommandCardList = RecommandCreditCard(summary)
        cardList = CreditCardSearch(recommandCardList)

        res = MonthlySpendingAndCreditcard(
            userId=userId, creditCardList=cardList
        ).json()
        # 메시지 응답 큐 pub
        ch.basic_publish(exchange="", routing_key=pub_queue_name, body=res)

        # 메시지 처리 완료 시 MQ에 처리 했다고 전달하는 함수
        ch.basic_ack(delivery_tag=method.delivery_tag)
    except Exception as e:
        print(e)


# sub 설정
channel.basic_qos(prefetch_count=1)
channel.basic_consume(
    queue=sub_queue_name, on_message_callback=callback, auto_ack=False
)
channel.start_consuming()
