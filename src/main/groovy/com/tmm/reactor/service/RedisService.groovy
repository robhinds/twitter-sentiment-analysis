package com.tmm.reactor.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
public class RedisService {
	
	private final String ALL_TWEETS = "ALL_TWEETS"

	@Autowired private RedisTemplate<String, Set> redisTemplate
	
	public void saveTweet( String tweet, List<String> countries ){
		redisTemplate.opsForSet().add( ALL_TWEETS, tweet )
		countries.each{
			redisTemplate.opsForSet().add( it, tweet )
		}
	}
	
}
