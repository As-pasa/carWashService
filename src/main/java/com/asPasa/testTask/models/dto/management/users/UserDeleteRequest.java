package com.asPasa.testTask.models.dto.management.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос на удаление пользователя")
public class UserDeleteRequest {
    @Schema(description = "Имя удаляемого пользователя")
    private String username;

}
