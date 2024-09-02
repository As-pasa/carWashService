package com.asPasa.testTask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class CreateWashTypeRequest {
    private final String name;
    private final Long duration;

}
