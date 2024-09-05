package com.asPasa.testTask.models.dto.management.service;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Ответ содержащий информацию о типе услуги")
public class WashTypeResponse {
    @Schema(description = "Наименование типа услуги")
    private String name;
    @Schema(description = "Ожидаемое время выполнения услуги")
    private Long estTime;


}
