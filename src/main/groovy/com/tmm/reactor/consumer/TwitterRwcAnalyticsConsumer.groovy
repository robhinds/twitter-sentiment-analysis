package com.tmm.reactor.consumer

import javax.inject.Inject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tmm.reactor.domain.SocialContent
import com.tmm.reactor.service.RedisService
import com.tmm.reactor.service.TweetAnalyticsService

import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static reactor.bus.selector.Selectors.$
import groovy.util.logging.Log4j


/**
 * Implements the Reactor {@link Consumer} interface that can be setup
 * to listen to the EventBus.  This class listens to the EventBus for 
 * "socialcontent" events and then performs some tweet analysis on them - checking if
 * they are Rugby World Cup related and then persists info to redis if relevant
 * 
 * @author robhinds
 */
@Service
@Log4j
class TwitterRwcAnalyticsConsumer implements Consumer<Event<SocialContent>> {
	
	@Autowired private TweetAnalyticsService tweetAnalyticsService
	@Autowired private RedisService redisService

	@Inject public TwitterRwcAnalyticsConsumer( EventBus eventBus ){
		eventBus.on($("socialcontent"), this )
	}
	
	public void accept(Event<SocialContent> event) {
		String tweet = event.getData().text
		if ( tweetAnalyticsService.isRwcTweet( tweet ) ) {
			List countries = tweetAnalyticsService.getCountriesFromTweet( tweet )
			log.info "Tweet: $tweet"
			log.info "Mentioned countries: $countries"
			redisService.saveTweet( tweet, countries )
		}
	}
}
