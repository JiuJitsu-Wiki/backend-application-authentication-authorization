package com.bjjmaster.backendapplication.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void configure() throws Exception {
        this.mockMvc.perform(get("/public/hello")).andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get("/private/hello")).andExpect(status().isForbidden());
        // swagger ui is aloud
        this.mockMvc.perform(get("/v3/api-docs")).andExpect(status().is2xxSuccessful());
    }


}