package com.bjjmaster.backendapplication.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String username;
}
