package com.asPasa.testTask.models.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ на запрос списка доступных для резервации слотов")
public class BookSLotsResponse {
    @Schema(description = "Список дат, на которые можно зарезервироваться")
    private List<String> dates;
    @Schema(description = "Тип услуги для регистрации")
    private String washType;
}
