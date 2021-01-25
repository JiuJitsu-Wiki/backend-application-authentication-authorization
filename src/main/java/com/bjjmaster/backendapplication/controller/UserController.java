package com.bjjmaster.backendapplication.controller;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // TODO: handle user/username already exists
    // TODO: email/password/username valitation
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        appUserService.registerAppUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam String email,
                                        @RequestParam String password) {
        appUserService.login(email, password);
        return ResponseEntity.ok("User registered successfully");
    }


}
