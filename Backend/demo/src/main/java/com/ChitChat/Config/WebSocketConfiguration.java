package com.ChitChat.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("socket");
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200");
        System.out.println("started");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
        //Messages sent from the client start with "/app" to indicate their destination on the server side.
        //Messages broadcasted from the server to clients are sent to topics starting with "/topic".
    }
}
