package com.asPasa.testTask.api;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.management.*;
import com.asPasa.testTask.services.WashTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final WashTypeService washTypeService;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy HH:mm", Locale.ENGLISH);


}
