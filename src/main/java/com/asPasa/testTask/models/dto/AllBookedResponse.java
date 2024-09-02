package com.asPasa.testTask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllBookedResponse {
    private List<String> booked;

}
