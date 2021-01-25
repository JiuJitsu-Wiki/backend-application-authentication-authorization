package com.bjjmaster.backendapplication.controller;

import com.bjjmaster.backendapplication.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void register() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("vaggelas@bjjmaster.com");
        userDTO.setPassword("1234");
        userDTO.setUsername("purplehero");
        this.mockMvc.perform(
                post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("mikros@bjjmaster.com");
        userDTO.setPassword("1234");
        userDTO.setUsername("purpleheroM");
        this.mockMvc.perform(
                post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
        this.mockMvc.perform(
                post("/users/login?email=mikros@bjjmaster.com&password=1234"))
                .andExpect(status().isOk());
    }
}