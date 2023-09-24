import pika

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)
    
def consume():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host="localhost"))
    channel = connection.channel()
    # 큐 선언
    channel.queue_declare(queue="realtime.queue",durable=True)
    # 큐 메시지 받으면 callback 실행하도록 바인딩
    channel.basic_consume(queue="realtime.queue", on_message_callback=callback, auto_ack=True)
    # 모든 바인딩 시작
    channel.start_consuming()
