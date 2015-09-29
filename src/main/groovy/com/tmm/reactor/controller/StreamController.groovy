package com.tmm.reactor.controller

import com.tmm.reactor.domain.SocialContent
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
public class HelloController {

    @Autowired private Twitter twitter
    @Autowired private ConnectionRepository connectionRepository
	@Autowired private EventBus eventBus
	@Autowired private TwitterStreamListener twitterStreamListener
	

    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        if ( !connectionRepository.findPrimaryConnection( Twitter ) ) {
            "redirect:/connect/twitter"
        } else {
			twitter.streamingOperations().sample( [ twitterStreamListener ] )
			model.addAttribute(twitter.userOperations().getUserProfile())
			CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends()
			model.addAttribute("friends", friends)
			"hello"
        }
    }
}