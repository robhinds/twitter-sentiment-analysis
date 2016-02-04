package com.tmm.reactor.service

import com.twitter.Extractor
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class TweetAnalyticsService {
	
	@Autowired Extractor extractor
		
    public List<String> getSymbolsFromTweet( String tweet ){
        extractor.extractCashtags( tweet ).unique()
    }
	
}