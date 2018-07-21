package org.cloris;

import org.cloris.utils.MD5Util;
import org.cloris.utils.ValidatorUtil;
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

//	@Autowired
//	RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	RedisTemplate<String, Object> template;

//	@Test
	public void contextLoads() {
		System.out.println(stringTemplate.opsForValue().get("k2"));

//		System.out.println("---------" + redisTemplate.opsForValue().get("user"));

	}

//	@Test
	public void test() {
		System.out.println(MD5Util.md5("123456"));
		System.out.println(MD5Util.inputPassToDbPass("123456", "1a2b3c4d"));
	}

//	@Test
	public void testValidatorUtil() {
		System.out.println(ValidatorUtil.isMobile("18912341234"));
		System.out.println(ValidatorUtil.isMobile("abc"));
	}

	@Test
	public void testTemplate() {
		template.opsForValue().set("k1", "v1");
		System.out.println(template.opsForValue().get("k1"));
	}

}
