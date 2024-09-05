package com.asPasa.testTask.models.dto.management.service;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на удаление типа услуги")
public class DeleteWashTypeRequest {
    @Schema(description = "Наименование удаляемого типа услуги")
    private String name;
}
