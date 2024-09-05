package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос получения списка слотов на день")
public class GetFreeSlotsForDayRequest {
    @Schema(description = "Тип услуги")
    private String washType;
    @Schema(description = "Дата желаемого бронирования")
    private String date;

}
