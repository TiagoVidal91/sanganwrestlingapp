package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.DTO.SignupRequest;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.User;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    RoleServiceImpl roleService;

    @Mock
    UserRepo userRepo;

    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    public class RegistrationTests {

        private final Set<String> rolesStr = new HashSet<>();

        @BeforeEach
        void setUp(){
            rolesStr.addAll(Arrays.asList("ADMIN","USER"));
        }

        @Test
        void registerUserSuccessfully() {
            //Arrange
            SignupRequest signUpRequest =
                    new SignupRequest("TestUser","testuser@email.com",
                            rolesStr,"abc123");

            Role roleAdmin = new Role(1L, "ADMIN");
            Role roleUser = new Role(2L, "USER");
            Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));

            when(userRepo.existsByUsername("TestUser")).thenReturn(false);
            when(userRepo.existsByEmail("testuser@email.com")).thenReturn(false);

            when(passwordEncoder.encode("abc123")).thenReturn("Encodedabc123");

            when(roleService.addRolesToUser(rolesStr)).thenReturn(roles);

            when(userRepo.save(any(User.class))).thenReturn(any(User.class));
            //Act
            User returnedUser = userService.registerUser(signUpRequest);

            //Assert
            assertEquals(returnedUser.getUsername(), signUpRequest.getUsername());
            assertEquals(returnedUser.getEmail(), signUpRequest.getEmail());
            verify(passwordEncoder,times(1)).encode("abc123");
        }

        @Test
        void usernameAlreadyExists() {
            //Arrange
            SignupRequest signUpRequest =
                    new SignupRequest("TestUser","testuser@email.com",
                            rolesStr,"abc123");

            when(userRepo.existsByUsername("TestUser")).thenReturn(true);

            //Act
            String message = assertThrows(RuntimeException.class,
                    () -> userService.registerUser(signUpRequest)).getMessage();

            //Assert
            assertEquals(message,"Error: Username is already in use!");
            verify(userRepo,times(1)).existsByUsername("TestUser");
        }

        @Test
        void emailAlreadyExists() {
            //Arrange
            SignupRequest signUpRequest =
                    new SignupRequest("TestUser","testuser@email.com",
                            rolesStr,"abc123");

            when(userRepo.existsByUsername("TestUser")).thenReturn(false);
            when(userRepo.existsByEmail("testuser@email.com")).thenReturn(true);

            //Act
            String message = assertThrows(RuntimeException.class,
                    () -> userService.registerUser(signUpRequest)).getMessage();

            //Assert
            assertEquals(message,"Error: Email is already in use!");

            verify(userRepo,times(1)).existsByUsername("TestUser");
            verify(userRepo,times(1)).existsByEmail("testuser@email.com");
        }
    }



    @Test
    void findByUsername() {
        Optional<User> user = Optional.of(new User("TestUser","testuser@email.com"
                ,"CodedPassword", LocalDate.now()));

        //Arrange
        when(userRepo.findByUsername("TestUser")).thenReturn(user);

        //Act
        Optional<User> userReturned = userService.findByUsername("TestUser");

        //Assert
        assertEquals(userReturned.get().getUsername(),user.get().getUsername());
        assertEquals(userReturned.get().getEmail(),user.get().getEmail());
        assertEquals(userReturned.get().getPassword(),user.get().getPassword());
        assertEquals(userReturned.get().getCreatingDate(),user.get().getCreatingDate());
        verify(userRepo,times(1)).findByUsername("TestUser");
    }
}