package org.cloris.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecKillMQSender {

	@Autowired
	AmqpTemplate amqpTemplate;

	private static Logger log = LoggerFactory.getLogger(SecKillMQSender.class);

	public void sendMessage(SecKillMessage message) {
		log.info("发送秒杀消息：" + message);
		amqpTemplate.convertAndSend(MQConfig.SECOND_KILL_QUEUE, message);
	}

}
