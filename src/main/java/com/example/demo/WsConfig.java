//************************************
//Program Name: WsConfig.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: aids in implementation of messagin
//************************************
package com.example.demo;

//imports
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//WsConfig class
@Configuration
@EnableWebSocketMessageBroker
public class WsConfig implements WebSocketMessageBrokerConfigurer {

    //registerStompEndpoints method
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/example").withSockJS();
    }//registerStompEndpoints

    //configureMessageBroker method
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue"); // Enable simple broker for topics and queues
        registry.setApplicationDestinationPrefixes("/app");
    }//end of configureMessageBroker
    
}//end of WsCOnfig class
