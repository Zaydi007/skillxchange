package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SkillXchangeController {

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
        // Process the form submission here
        // You can store user information in a database or perform any other actions
        
        // For example, you can print the submitted information to the console
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Skills: " + String.join(", ", skills));
        System.out.println("Skills to Learn: " + String.join(", ", skillsToLearn));
    
        // Redirect the user to a success page or another appropriate page
        return "redirect:/success"; // Redirects to a success page
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // Renders the login.html template
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
            
        // Redirect the user to a success page or another appropriate page
        return "redirect:/success"; // Redirects to a success page
    }
}

