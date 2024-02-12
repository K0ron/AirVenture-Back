package com.airventure.airventureback.authentication.application;

import com.airventure.airventureback.authentication.domain.entity.User;
import com.airventure.airventureback.authentication.domain.service.JwtTokenService;
import com.airventure.airventureback.authentication.domain.service.UserDetailsServiceImpl;
import com.airventure.airventureback.authentication.domain.service.UserLoginService;
import com.airventure.airventureback.authentication.domain.service.UserRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UserLoginService userLoginService;
    private final UserRegisterService userRegisterService;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationController (
            UserLoginService userLoginService,
            UserRegisterService userRegisterService,
            JwtTokenService jwtTokenService,
            UserDetailsServiceImpl userDetailsService
    ) {
        this.userLoginService = userLoginService;
        this.userRegisterService = userRegisterService;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userBody) throws Exception {
        try {
            userLoginService.login(userBody);
            String token = jwtTokenService.generateToken(userDetailsService.loadUserByUsername(userBody.getEmail()));
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userBody) throws Exception {
        System.out.println(userBody);
        try {
            return ResponseEntity.status(201).body(userRegisterService.UserRegister(userBody));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
