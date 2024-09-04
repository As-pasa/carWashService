package com.asPasa.testTask.models.dto.management;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWashTypeRequest {
    private String name;
    private Long duration;
}
