
package org.sid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/** @Author Salmi Ismail **/

@Configuration
@EnableWebSocketMessageBroker
public class WebSokcetConfiguration implements WebSocketMessageBrokerConfigurer {

	private static final String BROKER = "/topic";
	private static final String APP_DESTINATION_PREFIXE = "/app";
	private static final String ENDPOINT = "/ws";

	/** STOMP stands for Simple Text Oriented Messaging Protocol **/
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint(ENDPOINT).withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes(APP_DESTINATION_PREFIXE);
		registry.enableSimpleBroker(BROKER);
	}

}
