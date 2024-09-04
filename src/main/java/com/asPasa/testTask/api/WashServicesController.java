package com.asPasa.testTask.api;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.booking.WashTypesResponse;
import com.asPasa.testTask.models.dto.management.CreateWashTypeRequest;
import com.asPasa.testTask.models.dto.management.DeleteWashTypeRequest;
import com.asPasa.testTask.models.dto.management.UpdateWashTypeRequest;
import com.asPasa.testTask.models.dto.management.WashTypeResponse;
import com.asPasa.testTask.services.ScheduleService;
import com.asPasa.testTask.services.UserService;
import com.asPasa.testTask.services.WashTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wash-services")
@RequiredArgsConstructor
public class WashServicesController {
    private final ScheduleService scheduleService;
    private final WashTypeService washTypeService;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<WashTypesResponse> getAll() {
        return ResponseEntity.ok(new WashTypesResponse(washTypeService.findAll().stream().map(WashType::getName).collect(Collectors.toList())));
    }

    @PostMapping("/create")
    public ResponseEntity<WashTypeResponse> create(@RequestBody CreateWashTypeRequest request){
        WashType washType= new WashType(request.getWashTypeName(),request.getEstimatedTime());
        washTypeService.createWashType(washType);
        return ResponseEntity.ok(new WashTypeResponse(washType.getName(),washType.getRequiredMinutes()));
    }
    @PostMapping("/delete")
    public ResponseEntity<WashTypeResponse> deleteWashType(@RequestBody DeleteWashTypeRequest request){
        WashType washType = washTypeService.findByName(request.getName());
        washTypeService.deleteById(washType.getId());
        return ResponseEntity.ok(new WashTypeResponse(washType.getName(),washType.getRequiredMinutes()));
    }
    @PostMapping("/update")
    public ResponseEntity<WashTypeResponse> updateWashType(@RequestBody UpdateWashTypeRequest request){
        WashType oldType = washTypeService.findByName(request.getName());
        WashType newType = new WashType(request.getName(),request.getDuration());
        washTypeService.updateWashType(newType,oldType.getId());
        WashTypeResponse response = new WashTypeResponse(newType.getName(),newType.getRequiredMinutes());
        return ResponseEntity.ok(response);
    }
}
