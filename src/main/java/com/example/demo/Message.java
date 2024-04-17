package com.example.demo;

import java.time.LocalDateTime;

public class Message {
    private UserProfile sender;
    private UserProfile recipient;
    private String content;
    private LocalDateTime timestamp;

    public Message(UserProfile sender, UserProfile recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public UserProfile getSender() {
        return sender;
    }

    public void setSender(UserProfile sender) {
        this.sender = sender;
    }

    public UserProfile getRecipient() {
        return recipient;
    }

    public void setRecipient(UserProfile recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
