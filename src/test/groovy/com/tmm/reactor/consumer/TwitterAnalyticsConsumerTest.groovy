package com.tmm.reactor.consumer

import com.tmm.reactor.domain.SocialContent
import com.tmm.reactor.service.RedisService
import com.tmm.reactor.service.SentimentService
import com.tmm.reactor.service.TweetAnalyticsService
import com.twitter.Extractor
import org.junit.Test
import org.junit.Before

import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static reactor.bus.selector.Selectors.$
import static org.mockito.Mockito.*

class TwitterAnalyticsConsumerTest {

	private TwitterAnalyticsConsumer consumer
	private RedisService redis
	
	@Before public void setupConsumer(){
		EventBus bus = mock( EventBus )
		consumer = new TwitterAnalyticsConsumer( bus )
		consumer.tweetAnalyticsService = new TweetAnalyticsService()
		consumer.tweetAnalyticsService.extractor = new Extractor()
		consumer.sentimentService = [calculateSentimentScore: { String tweet -> 4 }] as SentimentService
		redis = [ saveTweet: { tweet, countries, score -> true } ] as RedisService
		consumer.redisService = redis
	}
	
	@Test public void consumerTweet(){
		consumer.accept( Event.wrap( new SocialContent( text: "sample #rwc2015 #wal tweet") ) )
	}
	
}
