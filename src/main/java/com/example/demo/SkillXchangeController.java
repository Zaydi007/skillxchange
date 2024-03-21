package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkillXchangeController {

    @GetMapping("/skillxchange")
    public String skillXchangeLandingPage(Model model) {
        model.addAttribute("pageTitle", "SkillXchange Landing Page");
        // Add more model attributes as needed for your Thymeleaf template
        return "skillxchange-landing";
    }
}

