package org.cloris.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

	private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

	@RabbitListener(queues = MQConfig.QUEUE)
	public void receive(String message) {
		log.info("收到消息：" + message);
	}

	@RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
	public void receiveTopic1(String message) {
		log.info("收到topic-1消息：" + message);
	}

	@RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
	public void receiveTopic2(String message) {
		log.info("收到topic-2消息：" + message);
	}

	@RabbitListener(queues = MQConfig.HEADERS_QUEUE)
	public void receiveHeaders(byte[] message) {
		log.info("收到header队列消息：" + new String(message));
	}

}
