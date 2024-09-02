package com.asPasa.testTask.models.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GetScheduleSlotsForDateRequest {
    private final Long WashTypeId;
    private final String scheduledDate;

}
