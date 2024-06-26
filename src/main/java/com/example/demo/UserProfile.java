//************************************
//Program Name: UserProfile.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: has methods for retrieving user information
//************************************
package com.example.demo;

//imports
import java.util.List;
import java.util.ArrayList;

//UserProfile class
public class UserProfile {
    private String username;
    private String password;
    private String[] skills;
    private String[] skillsToLearn;
    private List<String> messages;

    // Constructor
    public UserProfile(String username, String password, String[] skills, String[] skillsToLearn) {
        this.username = username;
        this.password = password;
        this.skills = skills;
        this.skillsToLearn = skillsToLearn;
        this.messages = new ArrayList<>(); // Initialize the messages list
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getSkillsToLearn() {
        return skillsToLearn;
    }

    public void setSkillsToLearn(String[] skillsToLearn) {
        this.skillsToLearn = skillsToLearn;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    //end of setters and getters
}//end of UserProfile
