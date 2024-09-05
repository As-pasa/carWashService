package com.asPasa.testTask.models.dto.management.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Ответ на запрос данных о пользователе")
public class UserDataResponse {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Роль пользователя")
    private String role;
    @Schema(description = "Адрес электронной почты пользователя")
    private String email;

}
