//************************************
//Program Name: ChatController.java
//Developer: XChange
//Date Created: 04/19/2024
//Version: 1.0
//Purpose: allows hello page to work
//************************************

package com.example.demo;

//imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//MyController class
@Controller
public class MyController {

    //hello method
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, this is generated using Thymeleaf!");
        return "hello";
    }//end of hello method
    
}//end of MyController
