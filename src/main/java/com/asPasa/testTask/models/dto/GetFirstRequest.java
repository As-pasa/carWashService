package com.asPasa.testTask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GetFirstRequest {
    private final Long userId;
    private final Long WashTypeId;

}
