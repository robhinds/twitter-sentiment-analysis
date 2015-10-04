package com.tmm.reactor.service

import org.junit.Test
import static org.junit.Assert.*

class TweetAnalyticsServiceTest {
	
	TweetAnalyticsService service = new TweetAnalyticsService()
	
	@Test public void validRwcTweet(){
		assertTrue service.isRwcTweet( "random valid tweet with upper case #RWC2015 hashtag " )
		assertTrue service.isRwcTweet( "random valid tweet with lower case #rwc2015 hashtag " )
	}
	
	@Test public void nonRwcTweet(){
		assertFalse service.isRwcTweet( "random tweet with random #funsies hashtag " )
		assertFalse service.isRwcTweet( "random tweet with random #rwd hashtag " )
	}
	
	@Test public void getSingleCountryFromTweet(){
		assertEquals( ["Wales"], service.getCountriesFromTweet( "crazy #rwc2015 tweet about #wal" ) ) 
	}
	
	@Test public void getMultipleCountriesFromTweet(){
		assertEquals( ["Fiji", "Wales"], service.getCountriesFromTweet( "crazy #rwc2015 tweet about #wal vs #fji" ).sort() )
	}
	
	@Test public void getMultipleUniqueCountriesFromTweet(){
		assertEquals( ["Fiji", "Wales"], service.getCountriesFromTweet( "crazy #rwc2015 tweet #wal to win - #IAmWales about #wal vs #fji" ).sort() )
	}

}
