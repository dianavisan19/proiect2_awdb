package com.awbd.booking.services;

import com.awbd.booking.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "notification")
public interface NotificationServiceProxy {
    @GetMapping("/notification")
    Notification findNotification();
}
