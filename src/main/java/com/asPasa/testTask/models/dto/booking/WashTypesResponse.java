package com.asPasa.testTask.models.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class WashTypesResponse {
    private List<String> washServices;
}
