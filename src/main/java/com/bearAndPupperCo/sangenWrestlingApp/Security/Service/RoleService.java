package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> findByName(String name);

    Set<Role> addRolesToUser(Set<String> strRoles);
}
