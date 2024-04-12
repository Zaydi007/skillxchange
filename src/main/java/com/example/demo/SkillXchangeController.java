package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SkillXchangeController {

    private final SkillXChangeDatabase skillXChangeDatabase;

    @Autowired
    public SkillXchangeController(SkillXChangeDatabase skillXChangeDatabase) {
        this.skillXChangeDatabase = skillXChangeDatabase;
    }

    @GetMapping("/skillxchange")
    public String skillXchangeLandingPage(Model model) {
        model.addAttribute("pageTitle", "SkillXchange Landing Page");
        // Add more model attributes as needed for your Thymeleaf template
        return "skillxchange-landing";
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        return "signup"; // Renders the signup.html template
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("skills") String[] skills,
                         @RequestParam("skillsToLearn") String[] skillsToLearn,
                         Model model) {
        // Store user information
        skillXChangeDatabase.saveUserData(username, password, skills, skillsToLearn);
        
        // Redirect the user to a success page or another appropriate page
        return "redirect:/main"; // Redirects to the main page
    }
}
