/************************************
//Program Name: NotificationService.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: functionality for the notifications service, saves to to text file, and an import from text file
//************************************
package com.example.demo;

//imports
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//NotifocationService class
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
    
    public void markNotificationAsAccepted(String username, String message) {
        // Implement logic to mark the notification as accepted
        // For example, you can update the status of the notification in the database
        logger.info("Notification accepted by {}: {}", username, message);
    }
    
    public void markNotificationAsDenied(String username, String message) {
        // Implement logic to mark the notification as denied
        // For example, you can update the status of the notification in the database
        logger.info("Notification denied by {}: {}", username, message);
    }//end of sendNotification

    //getNotificationsForUser method imports from text file
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
    }//end of getNotificationsForUser method
    
}//end NotificationSerice
