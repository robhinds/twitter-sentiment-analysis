package com.tmm.reactor.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

import groovy.util.logging.Log4j;

import org.springframework.beans.factory.annotation.Autowired

@Service
@Log4j
public class RedisService {
	
	private int version
	
	private final String ALL_TWEETS = "ALL_TWEETS_V$version"
	private final String ALL_SYMBOLS = "ALL_SYMBOLS_V$version"

	@Autowired private RedisTemplate<String, String> redisTemplate
	@Autowired private TweetAnalyticsService tweetAnalyticsService
	
	public void saveTweet( String tweet, List<String> symbols, Integer sentiment ){
		redisTemplate.opsForList().rightPush( ALL_TWEETS, tweet )
		symbols.collect{ it.toUpperCase() }.each{
			//add the score keyed by each symbol included in the tweet
			redisTemplate.opsForList().rightPush( it, sentiment.toString() )
			
			//Add the symbol to our known list of symbols
			if ( !getAllSymbols().contains( it ) ) redisTemplate.opsForList().rightPush( ALL_SYMBOLS, it )
		}
	}
	
	public List getAllTweets(){
		getSetValuesByKey( ALL_TWEETS )
	}
	
	public List getAllSymbols(){
		getSetValuesByKey( ALL_SYMBOLS )
	}
	
	public List getAllSymbolsWithScores(){
		getAllSymbols().collect{ String symbol ->
			List<Integer> scores = getSetValuesByKey( symbol ).collect{ Integer.parseInt( it ) }
			[ symbol:symbol, sentiment: ((scores.sum() / scores.size()) as Double), momentum: scores.size() ] 
		}
	}
	
	private List getSetValuesByKey( String key ){
		int listSize = redisTemplate.opsForList().size( key ) 
		redisTemplate.opsForList().range( key, 0, listSize )
	}
	
	public void resetAllData(){
		redisTemplate.delete( redisTemplate.keys("*") )
	}
}