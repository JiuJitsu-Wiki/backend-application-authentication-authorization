package com.bjjmaster.backendapplication.service;

import com.bjjmaster.backendapplication.DTO.UserDTO;

public interface AuthenticationService {
    UserDTO authenticate(String username, String password);
}
