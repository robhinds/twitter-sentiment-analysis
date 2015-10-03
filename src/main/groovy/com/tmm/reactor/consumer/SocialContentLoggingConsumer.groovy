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

	private AtomicInteger counter
	
	@Inject public SocialContentLoggingConsumer( EventBus eventBus ){
		eventBus.on($("socialcontent"), this )
		counter = new AtomicInteger( 0 )
	}
	
	public void accept(Event<SocialContent> event) {
		int count = counter.incrementAndGet()
		if ( count % 1000 == 0 )
			log.info "Tweets received: ${count}"
	}
}
