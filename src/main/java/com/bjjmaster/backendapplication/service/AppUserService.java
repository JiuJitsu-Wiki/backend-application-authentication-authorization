package com.bjjmaster.backendapplication.service;

import com.bjjmaster.backendapplication.DTO.RegisterUserDTO;
import com.bjjmaster.backendapplication.model.AppUser;

public interface AppUserService {
    AppUser registerAppUser(RegisterUserDTO registerUserDTO);
}
