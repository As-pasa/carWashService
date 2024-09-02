package com.asPasa.testTask.models.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateUserResponse {
    private final Long id;
    private final String username;
    private final  String email;

}
