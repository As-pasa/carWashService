package com.asPasa.testTask.api;

import com.asPasa.testTask.models.dto.CreateWashTypeRequest;
import com.asPasa.testTask.services.WashTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local")
@RequiredArgsConstructor
public class Local {
    private final WashTypeService service;

    @PostMapping("/create")
    public String createWashType(@RequestBody CreateWashTypeRequest request){
        return service.createWashType(request.getName(),request.getDuration()).toString();

    }
}
