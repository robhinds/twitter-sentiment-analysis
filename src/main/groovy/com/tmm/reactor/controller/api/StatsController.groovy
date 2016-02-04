package com.tmm.reactor.controller.api

import com.tmm.reactor.service.RedisService

import groovy.util.logging.Log4j;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired

import reactor.bus.Event;
import reactor.bus.EventBus

@Log4j
@RestController
@RequestMapping("/api/stats/")
public class StatsController {

    @Autowired private RedisService redisService

    @RequestMapping( value="symbols", method=RequestMethod.GET)
    public List tweetsByCountry() {
        redisService.getAllSymbolsWithScores()
    }
	
	@RequestMapping( value="reset", method=RequestMethod.GET)
	public List resetEverything() {
		log.info "RESETING EVERYTHING!!"
		redisService.resetAllData()
	}
}
