package com.bjjmaster.backendapplication.controller;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.service.AppUserService;
import com.bjjmaster.backendapplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private AppUserService appUserService;
    private AuthenticationService authenticationService;

    @Autowired
    public UserController(AppUserService appUserService, AuthenticationService authenticationService) {
        this.appUserService = appUserService;
        this.authenticationService = authenticationService;
    }

    // TODO: handle user/username already exists
    // TODO: email/password/username valitation
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        appUserService.registerAppUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> login(@RequestParam String email,
                                         @RequestParam String password) {
        UserDTO userDTO = authenticationService.authenticate(email, password);
        return ResponseEntity.ok(userDTO);
    }


}
