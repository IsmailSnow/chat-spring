
package org.sid.model;

import lombok.Getter;
import lombok.Setter;

/** @Author Salmi Ismail **/

@Setter
@Getter
public class ChatMessage {

	private MessageType type;
	private String content;
	private String sender;

}
