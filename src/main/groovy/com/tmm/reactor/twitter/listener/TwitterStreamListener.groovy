package com.tmm.reactor.twitter.listener

import com.tmm.reactor.domain.SocialContent
import org.springframework.social.twitter.api.StreamDeleteEvent
import org.springframework.social.twitter.api.StreamListener
import org.springframework.social.twitter.api.StreamWarningEvent
import org.springframework.social.twitter.api.Tweet
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import reactor.bus.EventBus
import reactor.bus.Event

/**
 * This class implements the {@link StreamListener} interface
 * to listen to Twitter streaming APIs. This listener just pushes 
 * Tweets onto the Reactor EventBus for other consumers to listen
 * to.
 * 
 * @author robhinds
 */
@Service
class TwitterStreamListener implements StreamListener {

	@Autowired private EventBus eventBus
	
	@Override public void onTweet(Tweet tweet) {
		SocialContent content = new SocialContent(
			text: tweet.getText(),
			postDate: tweet.getCreatedAt().toString(),
			provider: SocialContent.SocialProvider.TWITTER
		)
		eventBus.notify( "socialcontent", Event.wrap( content ) )
	}

	@Override public void onDelete(StreamDeleteEvent deleteEvent) { }
	@Override public void onLimit(int numberOfLimitedTweets) { }
	@Override public void onWarning(StreamWarningEvent warningEvent) { }
}