package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainPageController {
	
	@Autowired
    private SkillXChangeDatabase skillXChangeDatabase;
	
	@GetMapping("/main")
	public String showMainPage(Model model)
	{ return "main"; }
	
	@GetMapping("/notifications")
	public String showNotifications(Model model)
	{ return "notifications"; }
	
	@GetMapping("/userprofile")
	public String showUserProfile(Model model)
	{ return "userprofile"; }
	
	@GetMapping("/messageinbox")
	public String showMessages(Model model)
	{ return "messageinbox"; }
	
	
}
