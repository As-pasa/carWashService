package com.asPasa.testTask.models.dto.management.service;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание нового типа услуг")
public class CreateWashTypeRequest {
    @Schema(description = "Наименование типа услуги")
    private String washTypeName;
    @Schema(description = "Предполагаемое время выполнения услуги")
    private Long estimatedTime;

}
