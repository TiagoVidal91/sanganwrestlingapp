package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.DTO.SignupRequest;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.User;

import java.util.Optional;

public interface UserService {

    User registerUser(SignupRequest signUpRequest);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User saveUser(User user);
}
