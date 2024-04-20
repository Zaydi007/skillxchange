//************************************
//Program Name: SkillXChangeDatabase.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: includes methods for saving and getting user information to a text file as a database
//************************************
package com.example.demo;

//imports
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//SkillXChangeDatabase class
@Service
public class SkillXChangeDatabase {

	private static final Logger logger = LoggerFactory.getLogger(SkillXChangeDatabase.class);
	 
    private static final String USER_DATA_FILE = "userData.txt";
    private static final String MESSAGE_DATA_FILE = "messages.txt"; // Define the message data file

    private List<UserProfile> userProfiles;
    private List<String> notifications = new ArrayList<>();

	
    public SkillXChangeDatabase() {
        userProfiles = new ArrayList<>();
        loadUserData();
    }

	//loadUserData method
    private void loadUserData() {
        try {
            File userDataFile = new File(USER_DATA_FILE);
            BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4) {
                    String username = userData[0];
                    String password = userData[1];
                    String[] skills = userData[2].split(";");
                    String[] skillsToLearn = userData[3].split(";");
                    UserProfile userProfile = new UserProfile(username, password, skills, skillsToLearn);
                    userProfiles.add(userProfile);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of loadUserData method

	//isValidUser method checks if a user is Valid
    public boolean isValidUser(String username, String password) {
        for (UserProfile userProfile : userProfiles) {
            if (userProfile.getUsername().equals(username) && userProfile.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }//end of isValidUser method

	//saveUserData method
    public void saveUserData(String username, String password, String[] skills, String[] skillsToLearn) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true));
            StringBuilder userData = new StringBuilder();
            userData.append(username).append(",").append(password).append(",");
            userData.append(String.join(";", skills)).append(",").append(String.join(";", skillsToLearn));
            writer.write(userData.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of saveUserData

    public List<UserProfile> getAllUserProfiles() {
        return userProfiles;
    }

	//getUserProfile returns a profile
    public UserProfile getUserProfile(String username) {
        for (UserProfile userProfile : userProfiles) {
            if (userProfile.getUsername().equals(username)) {
                return userProfile;
            }
        }
        return null;
    }//end of getUserProfile

   /* public void sendMessage(String senderUsername, String receiverUsername, String message) {
        UserProfile senderProfile = getUserProfile(senderUsername);
        UserProfile receiverProfile = getUserProfile(receiverUsername);

        // Check if senderProfile or receiverProfile is null
        if (senderProfile == null || receiverProfile == null) {
            // Handle the case where either sender or receiver profile is not found
            System.out.println("Sender or receiver profile not found.");
            return;
        }

        // Add message to sender's messages
        senderProfile.getMessages().add("You: " + message);

        // Add message to receiver's messages
        receiverProfile.getMessages().add(senderUsername + ": " + message);
    }*/

	//sendMessage method
    public void sendMessage(String senderUsername, String receiverUsername, String message) {
        UserProfile senderProfile = getUserProfile(senderUsername);
        UserProfile receiverProfile = getUserProfile(receiverUsername);

        // Check if senderProfile or receiverProfile is null
        if (senderProfile == null || receiverProfile == null) {
            // Handle the case where either sender or receiver profile is not found
            System.out.println("Sender or receiver profile not found.");
            return;
        }

        // Add message to sender's messages
        senderProfile.getMessages().add("You: " + message);

        // Add message to receiver's messages
        receiverProfile.getMessages().add(senderUsername + ": " + message);
        
        // Save the messages to the database
        saveMessage(senderUsername, receiverUsername, message);
    }

    public List<String> getMessages() {
        List<String> allMessages = new ArrayList<>();
        for (UserProfile userProfile : userProfiles) {
            allMessages.addAll(userProfile.getMessages());
        }
        return allMessages;
    }//end of sendMessage method

	//findPotentialConnections method
    public List<UserProfile> findPotentialConnections(UserProfile userProfile) {
        List<UserProfile> potentialConnections = new ArrayList<>();
        // Implement the logic to find potential connections based on the user's skills to learn
        // For example, you might iterate over all user profiles and check if they have skills that the current user wants to learn
        for (UserProfile otherProfile : userProfiles) {
            if (!otherProfile.getUsername().equals(userProfile.getUsername())) {
                // Check if otherProfile's skills intersect with userProfile's skillsToLearn
                boolean hasCommonSkill = false;
                for (String skill : userProfile.getSkillsToLearn()) {
                    if (Arrays.asList(otherProfile.getSkills()).contains(skill)) {
                        hasCommonSkill = true;
                        break;
                    }
                }
                if (hasCommonSkill) {
                    potentialConnections.add(otherProfile);
                }
            }
        }
        return potentialConnections;
    }//end of findPotentialConnections method

	//saveMessage method
    public void saveMessage(String senderUsername, String receiverUsername, String message) {
        // Implement the logic to save messages to your database
        // For example, you can append the message to a file, store it in a relational database, or use any other persistence mechanism
        // Here, I'll demonstrate saving messages to a file for simplicity
        try {
            // Open the message file for appending
            BufferedWriter writer = new BufferedWriter(new FileWriter(MESSAGE_DATA_FILE, true));
            // Write the message in the format: senderUsername,receiverUsername,message
            writer.write(senderUsername + "," + receiverUsername + "," + message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of saveMessage method

	//sendNotificaton method
    public void sendNotification(String recipientUsername, String message) {
        String notification = "Notification sent to " + recipientUsername + ": " + message;
        addNotification(notification);
        logger.info("Notification sent: {}", notification);
    }//end of sendNotification

    public void addNotification(String notification) {
        notifications.add(notification);
    }

	//getNotification method
    public List<String> getNotifications() {
        logger.info("Retrieving notifications from the database...");
        logger.debug("Current notifications: {}", notifications);
        return notifications;
    }//end of getNotification
}//end of SkillXChangeDatabase method
