package org.cloris.utils;

import java.net.UnknownHostException;

import org.cloris.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//			throws UnknownHostException {
//		RedisTemplate<Object, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//		return template;
//	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//			throws UnknownHostException {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//		return template;
//	}
	
	@Bean
	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<String, User> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
		return template;
	}
}
