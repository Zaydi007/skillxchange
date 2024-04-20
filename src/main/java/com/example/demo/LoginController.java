//************************************
//Program Name: LoginController.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: allows login functionality with front end
//************************************
package com.example.demo;

//imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

//LoginController class
@Controller
public class LoginController {
    
    @Autowired
    private SkillXChangeDatabase skillXChangeDatabase;

    //showLoginForm method
    @GetMapping("/login")
    public String showLoginForm(Model model)
    {
        return "login"; // Renders the login.html template
    }//end of sbhowLoginForm

    //login method
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session)
    {
        // Authenticate user against the database
        if (skillXChangeDatabase.isValidUser(username, password))
        {
            // Set the current user in the session
            session.setAttribute("currentUser", username);
            return "redirect:/main"; // Redirects to the main page
        } else 
        {
            // Add an error message and render the login page again
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login"; // Redirects to the login page
        }
    }//end of login method

    //logout method
    // You can add a method to logout and invalidate the session
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        // Invalidate the session and clear the current user attribute
        session.invalidate();
        return "redirect:/login"; // Redirects to the login page after logout
    }//end of login method
    
}//end of LoginController
