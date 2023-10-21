package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WrestlingLockerRepo extends JpaRepository<LockerRoom, Long> {
}
