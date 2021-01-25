package com.bjjmaster.backendapplication.service.impl;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.bjjmaster.backendapplication.exception.CustomException;
import com.bjjmaster.backendapplication.model.AppUser;
import com.bjjmaster.backendapplication.repository.AppUserRepository;
import com.bjjmaster.backendapplication.security.JwtTokenProvider;
import com.bjjmaster.backendapplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AppUserRepository appUserRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationServiceImpl(AppUserRepository appUserRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDTO authenticate(String email, String password) {
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
