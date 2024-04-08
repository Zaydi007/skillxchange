package com.example.demo;

public class UserProfile {
    private String username;
    private String password;
    private String[] skills;
    private String[] skillsToLearn;

    // Constructor
    public UserProfile(String username, String password, String[] skills, String[] skillsToLearn) {
        this.username = username;
        this.password = password;
        this.skills = skills;
        this.skillsToLearn = skillsToLearn;
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
}