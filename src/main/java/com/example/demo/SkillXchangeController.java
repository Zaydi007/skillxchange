package com.example.demo;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SkillXchangeController {

	private static final Logger logger = LoggerFactory.getLogger(SkillXchangeController.class);

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

    @GetMapping("/messageinbox")
    public String showMessages(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Handle case where 'currentUser' is not present in the session
            // For example, redirect the user to the login page
            return "redirect:/login";
        }
        
        List<String> messages = skillXChangeDatabase.getMessages(); // Assuming getMessages() doesn't require parameters
        model.addAttribute("messages", messages);
        return "messageinbox"; // Return the Thymeleaf template for message inbox
    }
    
   /* @PostMapping("/connect")
    public String connect(@RequestParam("username") String username, HttpSession session, Model model) {
        String currentUser = (String) session.getAttribute("currentUser");
        String notificationMessage = currentUser + " connected with you on SkillXChange!";
        skillXChangeDatabase.sendNotification(username, notificationMessage);
        
        List<String> notifications = skillXChangeDatabase.getNotifications(); // Retrieve notifications
        
        logger.info("Notifications retrieved from database: {}", notifications);
        
        model.addAttribute("notifications", notifications); // Add notifications to the model
        return "redirect:/main"; // Redirects to the main page after connecting
    }
*/


}
