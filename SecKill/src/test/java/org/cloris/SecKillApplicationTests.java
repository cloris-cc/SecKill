package org.cloris;

import org.cloris.domain.User;
import org.cloris.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SecKillApplicationTests {

	@Autowired
	StringRedisTemplate stringTemplate;

	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

//	@Test
	public void contextLoads() {
		System.out.println(stringTemplate.opsForValue().get("k2"));

		User user = new User();
		user.setId(3);
		user.setName("小红");
		redisTemplate.opsForValue().set("user", user);
		System.out.println("---------" + redisTemplate.opsForValue().get("user"));

	}

	@Test
	public void test() {
		System.out.println(MD5Util.md5("123456"));
		System.out.println(MD5Util.inputPassToDbPass("123456", "1a2b3c4d"));
	}

}
