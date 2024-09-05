package com.asPasa.testTask.models.dto.management.users;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос на обновление пользовательской информации")
public class UserUpdateRequest {
    @Schema(description = "Старое имя пользователя")
    private String oldUsername;
    @Schema(description = "Новый адрес электронной почты пользователя")
    private String email;
    @Schema(description = "Новый пароль пользователя")
    private String password;
    @Schema(description = "Новое имя пользователя")
    private String newUsername;
    @Schema(description = "Новая роль пользователя")
    private String role;

}
