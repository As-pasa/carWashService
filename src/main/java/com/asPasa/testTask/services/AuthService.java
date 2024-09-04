package com.asPasa.testTask.services;

import com.asPasa.testTask.models.RoleType;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.dto.auth.JwtTokenResponse;
import com.asPasa.testTask.models.dto.auth.SignInRequest;
import com.asPasa.testTask.models.dto.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager manager;
    public JwtTokenResponse signUp(SignUpRequest request){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(RoleType.ROLE_USER)
                .password(encoder.encode(request.getPassword()))
                .build();
        userService.createUser(user);
        return new JwtTokenResponse(jwtService.generateToken(user));

    }
    public JwtTokenResponse signIn(SignInRequest request){
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserDetails user = userService.getUserDetailsService().loadUserByUsername(request.getName());
        String jwt = jwtService.generateToken(user);
        return new JwtTokenResponse(jwt);
    }
}
