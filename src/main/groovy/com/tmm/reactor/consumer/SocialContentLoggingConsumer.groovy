package com.tmm.reactor.consumer

import javax.inject.Inject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tmm.reactor.domain.SocialContent

import groovy.util.logging.Log4j;
import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static reactor.bus.selector.Selectors.$

import java.util.concurrent.atomic.AtomicInteger


/**
 * Implements the Reactor {@link Consumer} interface that can be setup
 * to listen to the EventBus.  This class listens to the EventBus for 
 * "socialcontent" events
 * 
 * @author robhinds
 */
@Service
@Log4j
class SocialContentLoggingConsumer implements Consumer<Event<SocialContent>> {

	private AtomicInteger counter=0
	
	@Inject public SocialContentLoggingConsumer( EventBus eventBus ){
		eventBus.on($("socialcontent"), this );
	}
	
	public void accept(Event<SocialContent> event) {
		log.info "Tweets received: ${counter.incrementAndGet()}"
	}
}
