package com.bjjmaster.backendapplication.service.impl;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceImplTest {

    @Autowired
    private AppUserServiceImpl appUserService;


    @Test
    void registerAppUser_success() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("vaggelas@bjjmaster.com");
        userDTO.setPassword("1234");
        userDTO.setUsername("purplehero");
        AppUser appUser = appUserService.registerAppUser(userDTO);
        assertNotEquals(0, appUser.getId());
    }
}