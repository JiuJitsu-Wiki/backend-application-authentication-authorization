package com.bjjmaster.backendapplication.service.impl;

import com.bjjmaster.backendapplication.DTO.RegisterUserDTO;
import com.bjjmaster.backendapplication.model.AppUser;
import com.bjjmaster.backendapplication.repository.AppUserRepository;
import com.bjjmaster.backendapplication.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AppUser registerAppUser(RegisterUserDTO registerUserDTO) {
        registerUserDTO.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        AppUser appUser = modelMapper.map(registerUserDTO, AppUser.class);
        appUserRepository.save(appUser);
        return appUser;
    }
}
