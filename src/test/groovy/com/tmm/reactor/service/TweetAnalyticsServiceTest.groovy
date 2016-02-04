package com.tmm.reactor.service

import com.twitter.Extractor
import org.junit.Test
import org.junit.Before
import static org.junit.Assert.*

class TweetAnalyticsServiceTest {
	
	TweetAnalyticsService service = new TweetAnalyticsService()
	
	@Before public void setupExtractor(){
		service.extractor = new Extractor()
	}
	
	@Test public void getSingleSymbolFromTweet(){
		assertEquals( ["GOOG"], service.getSymbolsFromTweet( 'crazy $GOOG tweet about #wal' ) ) 
	}
	
	@Test public void getMultipleSymbolsFromTweet(){
		assertEquals( ["AAPL", "GOOG"], service.getSymbolsFromTweet( 'who will win the moile wars $GOOG vs $AAPL' ).sort() )
	}
	
	@Test public void getMultipleUniqueSymbolsFromTweet(){
		assertEquals( ["AAPL", "GOOG"], service.getSymbolsFromTweet( 'lots of stocks stuff $GOOG $AAPL $GOOG' ).sort() )
	}
}