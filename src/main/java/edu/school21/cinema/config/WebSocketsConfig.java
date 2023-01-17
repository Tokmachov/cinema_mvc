package edu.school21.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketsConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //Use the built-in, message broker for subscriptions and broadcasting;
        //Route messages whose destination header begins with "/topic" to the broker.
        //For the built-in, simple broker the "/topic" and "/queue" prefixes do not have any special meaning.
        //They’re merely a convention to differentiate between pub-sub vs point-to-point messaging (i.e. many subscribers vs one consumer).
        //When using an external broker, please check the STOMP page of the broker to understand what kind of STOMP destinations and prefixes it supports
        config.enableSimpleBroker("/topic");
        //STOMP messages whose destination header begins with "/app" are routed to @MessageMapping methods in @Controller classes.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //endpoint to which client need to connect in order to make handshake
        registry.addEndpoint("/websocket/handshake/movie/chat");
        registry.addEndpoint("/websocket/handshake/movie/chat").withSockJS();
    }
}
