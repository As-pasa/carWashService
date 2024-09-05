package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на получение первой по времени услуги, забронированной пользователем")
public class UserFirstBookingRequest {
    @Schema(description = "Имя пользователя")
    private String userName;
}
