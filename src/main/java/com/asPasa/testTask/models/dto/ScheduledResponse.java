package com.asPasa.testTask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ScheduledResponse {
    private String Username;
    private String scheduledDate;
    private String WashType;
}
