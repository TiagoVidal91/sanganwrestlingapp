package com.bearAndPupperCo.sangenWrestlingApp.Configurations;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testAdminAccess() throws Exception {
        mockMvc.perform(get("/teiai-api/admin/test/test-admin-response"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUserAccessUnauthorized() throws Exception {
        mockMvc.perform(get("/teiai-api/admin/test/test-admin-response"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testUserAccess() throws Exception {
        mockMvc.perform(get("/teiai-api/test/test-server-response"))
                .andExpect(status().isOk());
    }



}