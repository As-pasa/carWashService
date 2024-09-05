package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Ответ на резервацию слота")
public class BookTimeResponse {
    @Schema(description = "Имя пользователя, получившего бронирование")
    private String username;
    @Schema(description = "Дата начала слота")
    private String date;
    @Schema(description = "Тип забронированной услуги")
    private String washType;
}
