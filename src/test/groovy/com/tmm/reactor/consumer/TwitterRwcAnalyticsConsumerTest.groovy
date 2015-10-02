package com.tmm.reactor.consumer

import com.tmm.reactor.domain.SocialContent
import com.tmm.reactor.service.RedisService
import com.tmm.reactor.service.TweetAnalyticsService
import org.junit.Test
import org.junit.Before

import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static reactor.bus.selector.Selectors.$
import static org.mockito.Mockito.*

class TwitterRwcAnalyticsConsumerTest {

	private TwitterRwcAnalyticsConsumer consumer
	private RedisService redis
	
	@Before public void setupConsumer(){
		EventBus bus = mock( EventBus )
		consumer = new TwitterRwcAnalyticsConsumer( bus )
		consumer.tweetAnalyticsService = new TweetAnalyticsService()
		redis = [ saveTweet: { tweet, countries -> true } ] as RedisService
		consumer.redisService = redis
	}
	
	@Test public void consumerTweet(){
		consumer.accept( Event.wrap( new SocialContent( text: "sample #rwc2015 #wal tweet") ) )
	}
	
}
