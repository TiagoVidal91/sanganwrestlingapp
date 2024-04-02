package com.bearAndPupperCo.sangenWrestlingApp.Security.DTO;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SignupRequest {

    private String username;

    private String email;

    private Set<String> role;

    private String password;
}
