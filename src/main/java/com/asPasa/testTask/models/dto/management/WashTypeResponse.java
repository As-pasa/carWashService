package com.asPasa.testTask.models.dto.management;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WashTypeResponse {
    private String name;
    private Long estTime;


}
