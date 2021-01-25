package com.bjjmaster.backendapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/private")
public class PrivateController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> helloWorld(@AuthenticationPrincipal AuthenticationPrincipal authenticationPrincipal) {
        System.out.println(authenticationPrincipal);
        return ResponseEntity.ok("Hello World!");
    }
}
