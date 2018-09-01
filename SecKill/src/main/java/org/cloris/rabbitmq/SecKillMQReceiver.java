package org.cloris.rabbitmq;

import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.SecKillService;
import org.cloris.vo.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecKillMQReceiver {

	@Autowired
	GoodsService goodsService;

	@Autowired
	SecKillService secKillService;

	private static Logger log = LoggerFactory.getLogger(SecKillMQSender.class);

	@RabbitListener(queues = MQConfig.SECOND_KILL_QUEUE)
	public void receiveMessage(SecKillMessage message) {
		log.info("收到秒杀消息：" + message);
		User user = message.getUser();
		Long goodsId = message.getGoodsId();

		GoodsVO goods = goodsService.findById(goodsId);
		// 减库存 下订单 写入秒杀订单
		secKillService.doSecKill(user, goods);

	}

}
