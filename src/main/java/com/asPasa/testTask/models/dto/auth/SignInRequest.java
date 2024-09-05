package com.asPasa.testTask.models.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос аутентификции в приложении")
public class SignInRequest {
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Пароль пользователя")
    private String password;
}
