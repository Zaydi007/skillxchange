package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainPageController {

    @Autowired
    private SkillXChangeDatabase skillXChangeDatabase;

    @Autowired
    private NotificationService notificationService; // Autowire the NotificationService

    @GetMapping("/main")
    public String showMainPage(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        
        if (currentUser != null) {
            UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
            
            if (userProfile != null) {
                List<UserProfile> potentialConnections = skillXChangeDatabase.findPotentialConnections(userProfile);
                model.addAttribute("potentialConnections", potentialConnections);
            }
        }
        
        return "main";
    }

    

    @GetMapping("/userprofile")
    public String showUserProfile(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        
        if (currentUser != null) {
            UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
            
            if (userProfile != null) {
                model.addAttribute("userProfile", userProfile);
            }
        }
        
        return "userprofile";
    }

    @GetMapping("/notifications")
    public String showNotifications(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");

        if (currentUser != null) {
            List<String> notifications = notificationService.getNotificationsForUser(currentUser);
            model.addAttribute("notifications", notifications);
            return "notifications"; // Return the notifications view
        }

        return "redirect:/login"; // Redirect to login page if user is not logged in
    }

}
