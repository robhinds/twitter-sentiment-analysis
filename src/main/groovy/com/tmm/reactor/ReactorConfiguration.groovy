package com.tmm.reactor

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

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
	
	@Bean JedisConnectionFactory jedisConnectionFactory() {
		new JedisConnectionFactory()
	}
   
	@Bean RedisTemplate< String, Set > redisTemplate() {
		final RedisTemplate< String, Set > template =  new RedisTemplate< String, Set >()
		template.setConnectionFactory( jedisConnectionFactory() )
		template.setKeySerializer( new StringRedisSerializer() )
		template.setHashValueSerializer( new GenericToStringSerializer< Set >( Set ) )
		template.setValueSerializer( new GenericToStringSerializer< Set >( Set ) )
		template
	}
}
