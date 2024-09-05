package com.asPasa.testTask.api;


import com.asPasa.testTask.models.User;

import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.management.service.CreateWashTypeRequest;
import com.asPasa.testTask.models.dto.management.service.DeleteWashTypeRequest;
import com.asPasa.testTask.models.dto.management.service.WashTypeResponse;
import com.asPasa.testTask.models.dto.management.users.UserDataResponse;
import com.asPasa.testTask.models.dto.management.users.UserDeleteRequest;
import com.asPasa.testTask.models.dto.management.users.UserUpdateRequest;
import com.asPasa.testTask.services.UserService;
import com.asPasa.testTask.services.WashTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final WashTypeService washTypeService;
    @Operation(summary = "Удаление пользователя из приложения")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete-user")
    public ResponseEntity<UserDataResponse> delete(@RequestBody UserDeleteRequest request){
        User user = userService.findByName(request.getUsername());
        userService.deleteByUsername(request.getUsername());
        return ResponseEntity.ok(new UserDataResponse(user.getName(),user.getRole().toString(),user.getEmail()));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-user")
    public ResponseEntity<UserDataResponse> update(@RequestBody UserUpdateRequest request){
        return ResponseEntity.ok(userService.updateUser(request));
    }
    @Operation(summary = "Создание нового типа услуги")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-wash-type")
    public ResponseEntity<WashTypeResponse> createWashType(@RequestBody CreateWashTypeRequest request){
        WashType washType= new WashType(request.getWashTypeName(),request.getEstimatedTime());
        washTypeService.createWashType(washType);
        return ResponseEntity.ok(new WashTypeResponse(washType.getName(),washType.getRequiredMinutes()));
    }
    @Operation(summary = "удаление типа услуги")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete-wash-type")
    public ResponseEntity<WashTypeResponse> deleteWashType(@RequestBody DeleteWashTypeRequest request){
        WashType washType = washTypeService.findByName(request.getName());
        washTypeService.deleteById(washType.getId());
        return ResponseEntity.ok(new WashTypeResponse(washType.getName(),washType.getRequiredMinutes()));
    }

}
