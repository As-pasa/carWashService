package com.asPasa.testTask.models.dto.management;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteWashTypeRequest {
    private String name;
}