package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Ответ на запрос о первой по времени услуге, забронированной пользователем")
public class UserFirstBookingResponse {
    @Schema(description ="Время начала забронированной услуги")
    private String date;
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Тип услуги")
    private String washType;
}
