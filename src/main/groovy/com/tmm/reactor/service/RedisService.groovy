package com.tmm.reactor.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
public class RedisService {

	@Autowired private RedisTemplate redisTemplate
	
	
	
}
