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

import com.twitter.Extractor;
import edu.stanford.nlp.pipeline.StanfordCoreNLP

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
   
	@Bean RedisTemplate< String, String > redisTemplate() {
		final RedisTemplate< String, String > template =  new RedisTemplate< String, String >()
		template.setConnectionFactory( jedisConnectionFactory() )
		template.setKeySerializer( new StringRedisSerializer() )
		template.setHashValueSerializer( new GenericToStringSerializer< String >( String ) )
		template.setValueSerializer( new GenericToStringSerializer< String >( String ) )
		template
	}
	
	/**
	 * Twitter open source library for parsing tweets - just re-using
	 * the cashtag parsing functionality
	 */
	@Bean public Extractor extractor(){
		new Extractor()
	}
	
	/**
	 * Stanfords NLP open source library - used for basic sentiment analysis
	 */
	@Bean public StanfordCoreNLP stanfordCoreNLP() {
		Properties props = new Properties()
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment, pos, lemma")
		new StanfordCoreNLP( props )
	}
}