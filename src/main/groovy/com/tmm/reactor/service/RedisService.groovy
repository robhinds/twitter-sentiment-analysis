package com.tmm.reactor.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
public class RedisService {
	
	private final String ALL_TWEETS = "ALL_TWEETS"

	@Autowired private RedisTemplate<String, Set> redisTemplate
	@Autowired private TweetAnalyticsService tweetAnalyticsService
	
	public void saveTweet( String tweet, List<String> countries ){
		redisTemplate.opsForSet().add( ALL_TWEETS, tweet )
		countries.each{
			redisTemplate.opsForSet().add( it, tweet )
		}
	}
	
	public Set getTweets(){
		getTweetsByKey( ALL_TWEETS )
	}
	
	public Map getTweetsByCountry(){
		tweetAnalyticsService.getCountries().collectEntries{ country ->
			Set tweets = getTweetsByKey( country )
			[ (country): [ number: tweets.size(), tweets: tweets ]  ]
		}
	}
	
	private Set getTweetsByKey( String country ){
		redisTemplate.opsForSet().members( country )
	}
	
}
