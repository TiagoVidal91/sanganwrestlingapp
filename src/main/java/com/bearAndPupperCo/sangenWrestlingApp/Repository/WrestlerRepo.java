package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WrestlerRepo extends JpaRepository<Wrestler, Long> {
}
