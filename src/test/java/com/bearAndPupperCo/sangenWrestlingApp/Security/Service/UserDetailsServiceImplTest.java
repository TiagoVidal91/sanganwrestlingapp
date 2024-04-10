package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.User;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepo userRepo;

    @Mock
    User user;

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "User_Test";
        User user = new User();
        user.setUsername(username);

        when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Act
        UserDetails userRetrieved = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userRetrieved);
        assertEquals(username, userRetrieved.getUsername());
        verify(userRepo, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "User_Test";
        User user = new User();
        user.setUsername(username);

        when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.empty());


        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        verify(userRepo, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserBuild(){
        String username = "User_Test";
        String password = "Password_Test";

        when(user.getUsername()).thenReturn(username);
        when(user.getPassword()).thenReturn(password);
        when(userRepo.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetailsService userDetailsService = new UserDetailsServiceImpl(userRepo);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());

    }
}