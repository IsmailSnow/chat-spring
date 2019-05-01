
package org.sid.listners;

import java.util.Objects;

import org.sid.model.ChatMessage;
import org.sid.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/** @Author Salmi Ismail **/

@Component
public class WebSocketEventListner {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListner.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListner(SessionConnectEvent event) {
		logger.info("Received a new web sokcet connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListner(SessionDisconnectEvent event) {

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if (!Objects.isNull(username)) {
			logger.info("User Disconnected : " + username);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(MessageType.LEAVE);
			chatMessage.setSender(username);
			messagingTemplate.convertAndSend("/topic/public", chatMessage);
		}

	}

}
