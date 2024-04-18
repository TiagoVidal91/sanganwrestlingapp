package com.bearAndPupperCo.sangenWrestlingApp.Security.Entity;

import com.bearAndPupperCo.sangenWrestlingApp.Security.DTO.LoginRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testSettersAndGetters() {
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("testuser@email.com");
        user.setCreatingDate(LocalDate.now());
        LocalDate date = user.getCreatingDate();

        //Act & Assert
        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals("testuser@email.com",user.getEmail());
        assertEquals(date,user.getCreatingDate());
    }
}