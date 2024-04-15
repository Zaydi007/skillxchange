package com.example.demo;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SkillXChangeDatabase {

    private static final String USER_DATA_FILE = "userData.txt";

    private List<UserProfile> userProfiles;

    public SkillXChangeDatabase() {
        userProfiles = new ArrayList<>();
        loadUserData();
    }

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
    }

    public boolean isValidUser(String username, String password) {
        for (UserProfile userProfile : userProfiles) {
            if (userProfile.getUsername().equals(username) && userProfile.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

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
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfiles;
    }

    public UserProfile getUserProfile(String username) {
        for (UserProfile userProfile : userProfiles) {
            if (userProfile.getUsername().equals(username)) {
                return userProfile;
            }
        }
        return null;
    }

    public int calculateCompatibility(String[] userSkills, String[] skillsToLearn, UserProfile otherUserProfile) {
        int compatibilityScore = 0;

        for (String skill : userSkills) {
            if (Arrays.asList(otherUserProfile.getSkills()).contains(skill)) {
                compatibilityScore += 2; // Increment the score for shared skills
            }
        }

        for (String skillToLearn : skillsToLearn) {
            if (Arrays.asList(otherUserProfile.getSkills()).contains(skillToLearn)) {
                compatibilityScore++; // Increment the score for skills the logged-in user needs to learn
            }
        }

        return compatibilityScore;
    }
    public List<UserProfile> findPotentialConnections(UserProfile currentUserProfile) {
        List<UserProfile> potentialConnections = new ArrayList<>();
        for (UserProfile userProfile : userProfiles) {
            if (userProfile != currentUserProfile) {
                int compatibilityScore = calculateCompatibility(currentUserProfile.getSkillsToLearn(), userProfile.getSkills(), userProfile);
                if (compatibilityScore > 0) {
                    potentialConnections.add(userProfile);
                }
            }
        }
        return potentialConnections;
    }

}
