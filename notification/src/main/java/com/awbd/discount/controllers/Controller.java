package com.awbd.notification.controllers;

import com.awbd.notification.config.PropertiesConfig;
import com.awbd.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private PropertiesConfig configuration;

    @GetMapping("/notification")
    public Notification getNotification(){

        return new Notification(configuration.getMonth(),configuration.getYear(), configuration.getVersionId());
    }
}
