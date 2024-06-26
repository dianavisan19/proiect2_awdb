package com.awbd.notification.controllers;

import com.awbd.notification.model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController  {
    private List<Notification> notifications = new ArrayList<>();

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
        return notifications;
    }

    @PostMapping("/notifications")
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        // Add notification to the list (or process as needed)
        notifications.add(notification);
        System.out.println("Received notification: " + notification.getMessage());
        return ResponseEntity.ok("Notification sent successfully");
    }
}
