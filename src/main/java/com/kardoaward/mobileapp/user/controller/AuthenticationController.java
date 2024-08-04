package com.kardoaward.mobileapp.user.controller;

import com.kardoaward.mobileapp.config.JwtCore;
import com.kardoaward.mobileapp.config.SecurityConfigurator;
import com.kardoaward.mobileapp.user.dto.UserShortDto;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Регистрация и авторизация пользователей", description = "Регистрация и логин (с получением jwt токена)")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfigurator securityConfigurator;
    private final JwtCore jwtCore;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Регистрация пользователя")
    public User register(@RequestBody UserShortDto userShortDto) {
        log.info("Registering user");
        userShortDto.setPassword(securityConfigurator.passwordEncoder().encode(userShortDto.getPassword()));
        return userService.create(userShortDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<?> login(@RequestBody UserShortDto user) {
        log.info("User login");
        Authentication authentication = null;
        try {
            authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    record AuthenticationResponse(String token) {
    }
}