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

  @GetMapping("/main")
  public String showMainPage(Model model, HttpSession session) {
      String currentUser = (String) session.getAttribute("currentUser");
      if (currentUser != null) {
          UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
          if (userProfile != null) {
              // Retrieve potential connections based on the user's skills to learn
              List<UserProfile> potentialConnections = skillXChangeDatabase.findPotentialConnections(userProfile);
              model.addAttribute("potentialConnections", potentialConnections);
          }
      }
      return "main";
  }

  @GetMapping("/notifications")
  public String showNotifications(Model model)
  { return "notifications"; }

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

  @GetMapping("/mainPageMessages")
  public String showMainPageMessages(Model model) {
      List<String> messages = skillXChangeDatabase.getMessages(); // Assuming this method retrieves messages from the database
      model.addAttribute("messages", messages);
      return "messageinbox"; 
  }


}

