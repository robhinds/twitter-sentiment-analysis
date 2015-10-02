package com.tmm.reactor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before

import reactor.bus.Event
import reactor.bus.EventBus
import reactor.fn.Consumer
import static reactor.bus.selector.Selectors.$
import static org.mockito.Mockito.*


@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = [ ReactorConfiguration ] )
public class ReactorConfigurationTest {

	@Autowired EventBus eventBus
	private Consumer<Event<Integer>> consumer1
	private Consumer<Event<Integer>> consumer2
	
	@Before public void setupConsumers(){
		consumer1 = mock( Consumer )
		consumer2 = mock( Consumer )
		eventBus.on($("testevent"), consumer1 )
		eventBus.on($("testevent"), consumer2 )
	}
	
	@Test public void multipleConsumersBindToEvent(){
		Event event = Event.wrap( 1 )
		event.setKey("testevent")
		eventBus.notify( "testevent", event )
		verify( consumer1, timeout(1000) ).accept( event )
		verify( consumer2, timeout(1000) ).accept( event )
	}

}