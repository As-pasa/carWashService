package com.asPasa.testTask.api;

import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.dto.CreateUserRequest;
import com.asPasa.testTask.models.dto.CreateUserResponse;
import com.asPasa.testTask.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request.getUsername(),request.getEmail());
        User saved =userService.persistUser(user);
        CreateUserResponse response= new CreateUserResponse(saved.getId(),saved.getUsername(),saved.getEmail());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/test")
    public String test(){
        return "test hello";
    }
}
