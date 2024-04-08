package com.example.demo;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillXChangeDatabase {

    private static final int DATABASE_SIZE = 50;
    private static final String USER_DATA_FILE = "userData.txt";

    
    private List<UserProfile> userProfiles;

    public SkillXChangeDatabase() {
        userProfiles = new ArrayList<>();
        loadUserData();
    }

    private void loadUserData() {
        try {
            File userDataFile = new File("userData.txt");
            BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4) {
                    String username = userData[0];
                    String password = userData[1];
                    String[] skills = userData[2].split(";"); // Split skills string into array
                    String[] skillsToLearn = userData[3].split(";"); // Split skills to learn string into array
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
}
