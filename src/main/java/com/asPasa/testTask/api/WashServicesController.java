package com.asPasa.testTask.api;

import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.booking.WashTypesResponse;
import com.asPasa.testTask.models.dto.management.service.CreateWashTypeRequest;
import com.asPasa.testTask.models.dto.management.service.DeleteWashTypeRequest;
import com.asPasa.testTask.models.dto.management.service.WashTypeResponse;
import com.asPasa.testTask.services.ScheduleService;
import com.asPasa.testTask.services.UserService;
import com.asPasa.testTask.services.WashTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/wash-services")
@RequiredArgsConstructor
public class WashServicesController {
    private final WashTypeService washTypeService;
    @Operation(summary = "Получение списка предоставляемых услуг")
    @GetMapping("/all")
    public ResponseEntity<WashTypesResponse> getAll() {
        return ResponseEntity.ok(new WashTypesResponse(washTypeService.findAll().stream().map(WashType::getName).collect(Collectors.toList())));
    }


}
