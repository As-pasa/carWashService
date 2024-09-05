package com.asPasa.testTask.models.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Jwt токен, ответ на регистрацию или аутентификацию пользователя")
public class JwtTokenResponse {
    @Schema(description = "Jwt токен")
    private String token;
}
