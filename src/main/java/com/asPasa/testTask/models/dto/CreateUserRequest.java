package com.asPasa.testTask.models.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CreateUserRequest {
    private final String username;
    private final String email;

}
