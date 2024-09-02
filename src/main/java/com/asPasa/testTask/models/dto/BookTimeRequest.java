package com.asPasa.testTask.models.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@RequiredArgsConstructor
@Data
public class BookTimeRequest {
    private final Long uId;
    private final Long washTypeId;
    private final String requestedTime;
}
