package com.bearAndPupperCo.sangenWrestlingApp.Security.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthEntryPointJwtTest {

    @Autowired
    private MockMvc mockMvc;

    private AuthEntryPointJwt authEntryPointJwt;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = mock(ObjectMapper.class);
        authEntryPointJwt = new AuthEntryPointJwt(objectMapper); // Assuming constructor injection
    }

    @Test
    public void testCommenceIntegration() throws Exception {

        AuthenticationException authException = new AuthenticationException("Full authentication is required to access this resource") {};

        final Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        expectedBody.put("error", "Unauthorized");
        expectedBody.put("message", authException.getMessage());
        expectedBody.put("path", "");

        // Perform a request that would trigger the commence method
        mockMvc.perform(get("/teiai-api/admin/test/test-admin-response"))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    String responseBody = response.getContentAsString();
                    String expectedJson = new Gson().toJson(expectedBody);

                    assertEquals(expectedJson, responseBody);
                });
    }


    @Test
    public void testCommenceContent() throws IOException, ServletException {
        // Prepare mocks
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = new AuthenticationException("Unauthorized") {};

        // Call the method under test
        authEntryPointJwt.commence(request, response, authException);

        // Verify ObjectMapper interaction
        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        expectedBody.put("error", "Unauthorized");
        expectedBody.put("message", authException.getMessage());
        expectedBody.put("path", request.getServletPath());

        verify(objectMapper, times(1)).writeValue(response.getOutputStream(), expectedBody);
    }
}