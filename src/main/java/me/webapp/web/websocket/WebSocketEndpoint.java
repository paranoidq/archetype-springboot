package me.webapp.web.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class WebSocketEndpoint {


    @MessageMapping("/hello")
    @SendTo("/topic/getResponse")
    public WebSocketResponse index(WebSocketRequest request) {
        return new WebSocketResponse();
    }
}
