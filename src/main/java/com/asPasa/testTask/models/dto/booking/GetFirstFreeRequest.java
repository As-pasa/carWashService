package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос получения первого доступного слота")
public class GetFirstFreeRequest {
    @Schema(description = "Тип запрашиваемой услуги")
    private String washType;

}
