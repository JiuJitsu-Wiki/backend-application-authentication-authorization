package com.bjjmaster.backendapplication.service.impl;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.exception.CustomException;
import com.bjjmaster.backendapplication.model.AppUser;
import com.bjjmaster.backendapplication.model.Role;
import com.bjjmaster.backendapplication.repository.AppUserRepository;
import com.bjjmaster.backendapplication.security.JwtTokenProvider;
import com.bjjmaster.backendapplication.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class AppUserServiceImpl implements AppUserService {

    private ModelMapper modelMapper;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AppUserServiceImpl(ModelMapper modelMapper,
                              AppUserRepository appUserRepository,
                              PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AppUser registerAppUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        AppUser appUser = modelMapper.map(userDTO, AppUser.class);
        appUser.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_ADMIN)));
        appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public UserDTO login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password, appUser.getRoles());
            authenticationManager.authenticate(authenticationToken);
            String jwtToken = jwtTokenProvider.createToken(email, appUser.getRoles());
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setJwt(jwtToken);
            return userDTO;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
