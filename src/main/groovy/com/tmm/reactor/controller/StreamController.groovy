package com.tmm.reactor.controller

import com.tmm.reactor.domain.SocialContent
import com.tmm.reactor.service.RedisService;
import com.tmm.reactor.twitter.listener.TwitterStreamListener
import javax.inject.Inject

import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.twitter.api.CursoredList
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.TwitterProfile
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired

import reactor.bus.Event;
import reactor.bus.EventBus

@Controller
@RequestMapping("/")
public class StreamController {

    @Autowired private Twitter twitter
    @Autowired private ConnectionRepository connectionRepository
    @Autowired private EventBus eventBus
    @Autowired private TwitterStreamListener twitterStreamListener
    @Autowired private RedisService redisService
	

    @RequestMapping(method=RequestMethod.GET)
    public String welcomePage() {
        if ( !connectionRepository.findPrimaryConnection( Twitter ) ) {
            "connect/twitterConnect"
        } else {
            "redirect:/stats"
        }
    }
	
    @RequestMapping(value="/stream", method=RequestMethod.GET)
    public String startStreaming() {
        if ( !connectionRepository.findPrimaryConnection( Twitter ) ) {
            "redirect:/"
        } else {
            twitter.streamingOperations().sample( [ twitterStreamListener ] )
            "redirect:/stats"
        }
    }
	
    @RequestMapping(value="/stats", method=RequestMethod.GET)
    public String stats( Model model ) {
        List countriesCount = redisService.getTweetsByCountry()
            .collect{ key, value -> [ country: key, number: value.number ] }
            .sort{ a,b -> b.number<=>a.number }
        model.addAttribute( "countryTweets", countriesCount )
        "stats"
    }
}
