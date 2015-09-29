package com.tmm.reactor

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import reactor.Environment
import reactor.bus.EventBus

@Configuration
class ReactorConfiguration {

	@Bean Environment env() {
		Environment.initializeIfEmpty().assignErrorJournal()
	}

	@Bean EventBus createEventBus(Environment env) {
		EventBus.create(env, Environment.THREAD_POOL)
	}
}
