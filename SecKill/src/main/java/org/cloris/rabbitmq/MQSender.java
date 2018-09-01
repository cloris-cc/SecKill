package org.cloris.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

	public void sendFanout(Object message) {
		String msg = message.toString();
		log.info("发送fanout消息：" + msg);
		amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", message);
	}

	public void sendHeader(Object message) {
		// 好困啊
		String msg = message.toString();
		log.info("发送header消息：" + msg);

		MessageProperties properties = new MessageProperties();
		properties.setHeader("header-1", "value-1");
		properties.setHeader("header-2", "value-2");

		Message obj = new Message(msg.getBytes(), properties);
		amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
	}

}
