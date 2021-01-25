package com.bjjmaster.backendapplication.service.impl;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.model.AppUser;
import com.bjjmaster.backendapplication.model.Role;
import com.bjjmaster.backendapplication.repository.AppUserRepository;
import com.bjjmaster.backendapplication.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class AppUserServiceImpl implements AppUserService {

    private ModelMapper modelMapper;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(ModelMapper modelMapper,
                              AppUserRepository appUserRepository,
                              PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser registerAppUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        AppUser appUser = modelMapper.map(userDTO, AppUser.class);
        appUser.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_ADMIN)));
        appUserRepository.save(appUser);
        return appUser;
    }


}
