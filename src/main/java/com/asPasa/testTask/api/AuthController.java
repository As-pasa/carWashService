package com.asPasa.testTask.api;

import com.asPasa.testTask.models.dto.auth.JwtTokenResponse;
import com.asPasa.testTask.models.dto.auth.SignInRequest;
import com.asPasa.testTask.models.dto.auth.SignUpRequest;
import com.asPasa.testTask.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;


    @PostMapping("/sign-up")
    public JwtTokenResponse signUp(@RequestBody SignUpRequest request){
        return auth.signUp(request);
    }
    @PostMapping("/sign-in")
    public JwtTokenResponse signIn(@RequestBody SignInRequest request){
        return auth.signIn(request);
    }
}
