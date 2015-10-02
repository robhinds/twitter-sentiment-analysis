package com.tmm.reactor.listener

import com.tmm.reactor.twitter.listener.TwitterStreamListener

import org.junit.Test
import org.junit.Before
import org.springframework.social.twitter.api.Tweet

import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static org.mockito.Mockito.*

import java.util.Date;

class TwitterStreamListenerTest {
	
	TwitterStreamListener listener
	EventBus eventBus
	
	@Before public void setupListener(){
		listener = new TwitterStreamListener()
		eventBus = mock( EventBus )
		listener.eventBus = eventBus
	}
	
	@Test public void tweetsAddedToEventBus(){
		listener.onTweet( new Tweet(1, "sample tweet", new Date(), "rob_hinds", "/image.jpeg", 2, 3, "UK", "Web") )
		verify( eventBus ).notify( eq("socialcontent"), any( Event ) )
	}	
}