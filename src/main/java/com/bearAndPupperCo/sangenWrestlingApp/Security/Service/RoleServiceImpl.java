package com.bearAndPupperCo.sangenWrestlingApp.Security.Service;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Set<Role> addRolesToUser(Set<String> strRoles) {
        return strRoles.stream()
                .map(this::safeFindByName)
                .collect(Collectors.toSet());
    }
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepo.findByName(name);
    }
    private Role safeFindByName(String roleStr) {
        return findByName(roleStr)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
