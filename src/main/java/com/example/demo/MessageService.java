package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private SkillXChangeDatabase skillXChangeDatabase;

    public void sendMessage(String sender, String recipient, String message) {
        UserProfile senderProfile = skillXChangeDatabase.getUserProfile(sender);
        UserProfile recipientProfile = skillXChangeDatabase.getUserProfile(recipient);
        
        if (senderProfile != null && recipientProfile != null) {
            // Assuming SkillXChangeDatabase has a method to save messages
            skillXChangeDatabase.saveMessage(sender, recipient, message);
        } else {
            System.out.println("Sender or recipient profile not found.");
        }
    }
    
}
