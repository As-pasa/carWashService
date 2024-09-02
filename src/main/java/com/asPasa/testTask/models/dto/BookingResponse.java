package com.asPasa.testTask.models.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class BookingResponse {
    private final String userName;
    private String washType;
    private Date bookedDate;
}
