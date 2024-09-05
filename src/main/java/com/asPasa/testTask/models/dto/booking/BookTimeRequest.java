package com.asPasa.testTask.models.dto.booking;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос бронирования слота")
public class BookTimeRequest {
    @Schema(description = "Имя пользователя")
    private String username;
    @Schema(description = "Дата начала слота")
    private String date;
    @Schema(description = "Тип услуги в слоте")
    private String serviceType;
}
