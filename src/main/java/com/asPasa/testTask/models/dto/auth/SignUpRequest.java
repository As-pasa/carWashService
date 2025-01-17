package com.asPasa.testTask.models.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос регистрации пользователя")
public class SignUpRequest {
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Адрес электронной почты пользователя")
    private String email;
    @Schema(description = "Пароль пользователя")
    private String password;
}
