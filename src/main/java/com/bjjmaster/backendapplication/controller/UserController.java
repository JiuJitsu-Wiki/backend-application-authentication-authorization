package com.bjjmaster.backendapplication.controller;

import com.bjjmaster.backendapplication.DTO.RegisterUserDTO;
import com.bjjmaster.backendapplication.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDTO registerUserDTO) {
        appUserService.registerAppUser(registerUserDTO);
        return ResponseEntity.ok("giname");
    }
}
