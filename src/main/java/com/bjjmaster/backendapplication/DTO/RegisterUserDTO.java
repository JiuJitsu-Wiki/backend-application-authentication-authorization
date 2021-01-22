package com.bjjmaster.backendapplication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
}
