package org.cloris.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

	private static Logger log = LoggerFactory.getLogger(MQSender.class);

	@Autowired
	AmqpTemplate amqpTemplate;

	public void send(Object message) {

		String msg = message.toString();
		log.info("发送消息：" + msg);
		amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);

	}

	public void sendTopic(Object message) {
		String msg = message.toString();
		log.info("发送topic消息：" + msg);
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", message + "1");
		amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", message + "2");
	}

}
