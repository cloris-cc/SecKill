package org.cloris.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	public static final String SECOND_KILL_QUEUE = "secondKill.queue";

	public static final String QUEUE = "queue";

	public static final String TOPIC_EXCHANGE = "topicExchange";
	public static final String FANOUT_EXCHANGE = "fanoutExchange";
	public static final String HEADERS_EXCHANGE = "headersExchange";

	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";
	public static final String HEADERS_QUEUE = "headers.queue";

	public static final String ROUTING_KEY1 = "topic.key1";
	public static final String ROUTING_KEY2 = "topic.#";

	@Bean
	public Queue secKillQueue() {
		return new Queue(SECOND_KILL_QUEUE, true);
	}

	/**
	 * Exchanges : direct 模式
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}

	/**
	 * Exchanges : topic 模式
	 * 
	 * Description : 将 Exchange 和 Queue 绑定
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}

	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
	}

	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
	}

	/**
	 * Exchanges : fanout 广播模式
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
	}

	/**
	 * Exchanges : headers 模式
	 */
	@Bean
	public HeadersExchange headersExchange() {
		return new HeadersExchange(HEADERS_EXCHANGE);
	}

	@Bean
	public Queue headersQueue1() {
		return new Queue(HEADERS_QUEUE, true);
	}

	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<>();
		map.put("header-1", "value-1");
		map.put("header-2", "value-2");
		return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(map).match();
	}
}
