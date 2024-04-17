package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainPageController {

  @Autowired
    private SkillXChangeDatabase skillXChangeDatabase;
  	private final ConnectDatabase connectDatabase;
  	
    public MainPageController(ConnectDatabase connectDatabase) {
        this.connectDatabase = connectDatabase;
    }

  /*@GetMapping("/main")
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
  }*/
  
  @GetMapping("/main")
  public String showMainPage(Model model, HttpSession session)
  {
	  String currentUser = (String) session.getAttribute("currentUser");
	  if (currentUser != null) {
          UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
          if (userProfile != null) {
              // Retrieve potential connections based on the user's skills to learn
              UserProfile firstConnection = skillXChangeDatabase.getFirstConnection(userProfile);
              model.addAttribute("firstConnection", firstConnection);
              
              UserProfile secondConnection = skillXChangeDatabase.getSecondConnection(userProfile);
              model.addAttribute("secondConnection", secondConnection);
              
              UserProfile thirdConnection = skillXChangeDatabase.getThirdConnection(userProfile);
              model.addAttribute("thirdConnection", thirdConnection);
          }
	  }
	  return "main";
  }
  
  @GetMapping("/firstconnect")
  public String firstConnectionMessage()
  {
	  return "firstconnect";
  }
  
  @PostMapping("/firstconnect")
  public String firstConnectionSent(Model model, HttpSession session)
  {
	  String currentUser = (String) session.getAttribute("currentUser");
	  UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile sender = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile firstConnection = skillXChangeDatabase.getFirstConnection(userProfile);
      connectDatabase.saveConnectRequests(firstConnection, sender);
	  return "redirect:/main";
  }
  
  @GetMapping("/secondconnect")
  public String secondConnectionMessage()
  {
	  return "secondconnect";
  }
  
  @PostMapping("/secondconnect")
  public String secondConnectionSent(Model model, HttpSession session)
  {
	  String currentUser = (String) session.getAttribute("currentUser");
	  UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile sender = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile secondConnection = skillXChangeDatabase.getSecondConnection(userProfile);
      connectDatabase.saveConnectRequests(secondConnection, sender);
	  return "redirect:/main";
  }
  
  @GetMapping("/thirdconnect")
  public String thirdConnectionMessage()
  {
	  return "thirdconnect";
  }
  
  @PostMapping("/thirdconnect")
  public String thirdConnectionSent(Model model, HttpSession session)
  {
	  String currentUser = (String) session.getAttribute("currentUser");
	  UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile sender = skillXChangeDatabase.getUserProfile(currentUser);
      UserProfile thirdConnection = skillXChangeDatabase.getThirdConnection(userProfile);
      connectDatabase.saveConnectRequests(thirdConnection, sender);
	  return "redirect:/main";
  }
  
  @GetMapping("/notifications")
  public String showNotifications(Model model, HttpSession session)
  { 
	  String currentUser = (String) session.getAttribute("currentUser");
	  if(currentUser != null)
	  {
		  //retrieving the current user's profile
		  UserProfile userProfile = skillXChangeDatabase.getUserProfile(currentUser);
		  if(userProfile != null)
		  {
			  List<UserProfile> requests = userProfile.getRequests();
			  //adding requests to the notifications page
			  model.addAttribute("requests", requests); 
		  }
	  }
	 return "notifications";
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


  @GetMapping("/messageinbox")
  public String showMessages(Model model)
  { return "messageinbox"; }



}
