package com.awbd.booking.services;

import com.awbd.booking.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "notification")
public interface NotificationServiceProxy {
    @GetMapping("/notifications")
    List<Notification> getAllNotifications();

    @PostMapping("/notifications")
    ResponseEntity<String> sendNotification(@RequestBody Notification notification);
}
