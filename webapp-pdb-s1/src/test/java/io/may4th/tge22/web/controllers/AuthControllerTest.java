package io.may4th.tge22.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.may4th.tge22.domain.api.UserRepository;
import io.may4th.tge22.web.payload.SignUpRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void signin() throws Exception {
        mockMvc
            .perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeSignUpRequestAsString("user", "pass")))
            .andExpect(status().isNotFound());

        mockMvc
            .perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeSignUpRequestAsString("user", "pass")))
            .andExpect(status().isOk());

        mockMvc
            .perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeSignUpRequestAsString("user", "zzz")))
            .andExpect(status().isUnauthorized());

        var token = objectMapper
            .readValue(mockMvc
                .perform(post("/api/auth/signin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(writeSignUpRequestAsString("user", "pass")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray(), Map.class)
            .get("accessToken");

        mockMvc
            .perform(get("/api/auth/me")
                .header(AUTHORIZATION_HEADER, token))
            .andExpect(status().isOk());
    }

    @Test
    void signup() throws Exception {
        var token = objectMapper
            .readValue(mockMvc
                .perform(post("/api/auth/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(writeSignUpRequestAsString("user", "pass")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray(), Map.class)
            .get("accessToken");

        mockMvc
            .perform(get("/api/auth/me")
                .header(AUTHORIZATION_HEADER, token))
            .andExpect(status().isOk());
    }

    @Test
    void me() throws Exception {
        var token = objectMapper
            .readValue(mockMvc
                .perform(post("/api/auth/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(writeSignUpRequestAsString("user", "pass")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray(), Map.class)
            .get("accessToken");

        mockMvc
            .perform(get("/api/auth/me")
                .header(AUTHORIZATION_HEADER, token))
            .andExpect(status().isOk());

        mockMvc
            .perform(get("/api/auth/me"))
            .andExpect(status().isUnauthorized());
    }

    String writeSignUpRequestAsString(String username, String password) throws Exception {
        return objectMapper
            .writeValueAsString(new SignUpRequest()
                .setUsername(username)
                .setPassword(password));
    }
}
