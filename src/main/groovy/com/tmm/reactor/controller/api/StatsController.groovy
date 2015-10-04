package com.tmm.reactor.controller.api

import com.tmm.reactor.service.RedisService

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired

import reactor.bus.Event;
import reactor.bus.EventBus

@RestController
@RequestMapping("/api/stats/")
public class StatsController {

    @Autowired private RedisService redisService
	

    @RequestMapping( value="country", method=RequestMethod.GET)
    public Map tweetsByCountry() {
        redisService.getTweetsByCountry()
    }
}