package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.DTO.SignupRequest;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.User;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepo userRepo, RoleService roleService, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(SignupRequest signUpRequest) {

        if (existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already in use!");
        }
        if (existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                LocalDate.now());

        // Set user roles
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = roleService.addRolesToUser(strRoles);
        user.setRoles(roles);

        // Save user
        saveUser(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }


}
