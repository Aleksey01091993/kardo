package com.kardoaward.mobileapp.user.controller;

import com.kardoaward.mobileapp.config.JwtCore;
import com.kardoaward.mobileapp.config.SecurityConfigurator;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfigurator securityConfigurator;
    private final JwtCore jwtCore;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        user.setPassword(securityConfigurator.passwordEncoder().encode(user.getPassword()));
        return userService.create(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody User user) {
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

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}


record AuthenticationResponse(String token) {
}
