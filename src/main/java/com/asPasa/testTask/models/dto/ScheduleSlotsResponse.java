package com.asPasa.testTask.models.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Data
public class ScheduleSlotsResponse {
    private final List<String> dates;
    private final String washType;

}
