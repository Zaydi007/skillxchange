package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ConnectionController {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/connect")
    public String connect(HttpSession session, @RequestParam("requestedUsername") String requestedUsername, RedirectAttributes redirectAttributes) {
        String requesterUsername = (String) session.getAttribute("currentUser");

        if (requesterUsername == null || requestedUsername == null) {
            logger.error("Invalid input: requesterUsername or requestedUsername is null");
            redirectAttributes.addFlashAttribute("error", "Invalid input for connection request.");
            return "redirect:/main";
        }

        try {
            // Send the connection request notification using the NotificationService
            notificationService.sendNotification(requestedUsername, requesterUsername, requesterUsername + " wants to connect!");
            logger.info("Connection request sent from {} to {}", requesterUsername, requestedUsername);
            redirectAttributes.addFlashAttribute("success", "Connection request sent successfully.");
        } catch (Exception e) {
            logger.error("Failed to send connection request from {} to {}: {}", requesterUsername, requestedUsername, e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Failed to send connection request. Please try again later.");
        }

        // Redirect to the main page on successful connection request
        return "redirect:/main";
    }
}
