package com.bearAndPupperCo.sangenWrestlingApp.Security.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);

}
