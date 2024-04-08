package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private SkillXChangeDatabase skillXChangeDatabase;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // Renders the login.html template
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        // Authenticate user against the database
        if (skillXChangeDatabase.isValidUser(username, password)) {
            // Redirect to a success page after successful login
            return "redirect:/success"; // Redirects to a success page
        } else {
            // Add an error message and render the login page again
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login"; // Redirects to the login page
        }
    }

}
