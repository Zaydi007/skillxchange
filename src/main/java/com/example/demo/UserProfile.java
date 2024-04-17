package com.example.demo;
import java.util.List;

public class UserProfile {
    private String username;
    private String password;
    private String[] skills;
    private String[] skillsToLearn;
    private List<UserProfile> connections;
    private List<UserProfile> requests;

    // Constructor
    public UserProfile(String username, String password, String[] skills, String[] skillsToLearn) {
        this.username = username;
        this.password = password;
        this.skills = skills;
        this.skillsToLearn = skillsToLearn;
        this.connections = null;
        this.requests = null;
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
    
    public List<UserProfile> getConnections()
    { return connections; }
    
    public List<UserProfile> getRequests()
    { return requests; }
    
    //Method to send a connect request
    public void sendConnect(UserProfile receiver)
    {
    	Notification connect = new Notification(this, receiver);
    	receiver.addConnect(connect);
    }
    
    //Method to receive a connect request
    public void addConnect(Notification connect)
    { 
    	requests.add(connect.getSender()); 
    }
    
    //Method that adds a user to the user's connection list after acceptance
    public void addConnection(Notification connect)
    { connections.add(connect.getSender()); }
}
