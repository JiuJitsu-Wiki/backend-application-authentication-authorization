package com.bjjmaster.backendapplication.security;

import com.bjjmaster.backendapplication.model.AppUser;
import com.bjjmaster.backendapplication.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BjjMasterUserDetails implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public BjjMasterUserDetails(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);

        return org.springframework.security.core.userdetails.User//
                .withUsername(appUser.getEmail())//
                .password(appUser.getPassword())//
                .authorities(appUser.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}


