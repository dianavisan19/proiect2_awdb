package com.awbd.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Notification {
    private int month;
    private int year;
    private String versionId;
}
