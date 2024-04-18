package com.example.demo;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private static final String NOTIFICATION_DATA_FILE = "notifications.txt";

 // Method to send a notification
    public void sendNotification(String recipientUsername, String senderUsername, String message) {
        saveNotification(recipientUsername, senderUsername, message);
        logger.info("Notification sent to {} from {}: {}", recipientUsername, senderUsername, message);
    }

    // Method to save a notification in the file
    private void saveNotification(String recipientUsername, String senderUsername, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTIFICATION_DATA_FILE, true))) {
            writer.write(recipientUsername + "," + senderUsername + "," + message);
            writer.newLine();
        } catch (IOException e) {
            logger.error("Error saving notification to file: {}", e.getMessage());
        }
    }

    public List<String> getNotificationsForUser(String username) {
        List<String> notifications = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFICATION_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3 && parts[0].equals(username)) {
                    notifications.add(parts[2]); // Add only the message part to notifications list
                }
            }
        } catch (IOException e) {
            logger.error("Error reading notifications from file: {}", e.getMessage());
        }
        return notifications;
    }
    
}
