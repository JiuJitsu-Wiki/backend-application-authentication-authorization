package com.bjjmaster.backendapplication.service;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.model.AppUser;

public interface AppUserService {
    AppUser registerAppUser(UserDTO userDTO);

    UserDTO login(String email, String password);
}
