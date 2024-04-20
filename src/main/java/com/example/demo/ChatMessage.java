//************************************
//Program Name: ChatMessage.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: aid in the ability for message to be sent and stored
//************************************

package com.example.demo;

//ChatMessage class
public class ChatMessage {

    //class attributes
    private String content;
    private String sender;
    private MessageType type;

    //MessageType
    public enum MessageType {
        CHAT, LEAVE, JOIN
    }//end of MessageType

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    //end of getters and setters
    
}//end of ChatMessage

