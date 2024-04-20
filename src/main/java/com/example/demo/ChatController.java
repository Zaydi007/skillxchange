//************************************
//Program Name: ChatController.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: Chat controller to work with front end
//************************************
package com.example.demo;

//imported files
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

//ChatController class
@Controller
public class ChatController {

    //Handles chat registration by storing the sender's username in session attributes
    //and forwarding the chat message to the "/topic/public" destination for broadcasting.
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }/// end of method

    //method to send a message
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }//end ChatMessage
    
}//end of ChatController
