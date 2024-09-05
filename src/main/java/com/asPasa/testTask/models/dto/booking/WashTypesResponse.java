package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Schema(description = "Ответ на запрос о списке всех доступных услуг")
public class WashTypesResponse {
    @Schema(description = "Список доступных услуг")
    private List<String> washServices;
}
