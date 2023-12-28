package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WrestlerRepo extends JpaRepository<Wrestler, Long> {

    @Query(value = "SELECT * FROM wrestler r WHERE r.wrestling_locker_room_id = ?1", nativeQuery = true)
    List<Wrestler> findWrestlerByWrestlingLockerRoomId(Long lockerId);

    @Query(value = "SELECT * FROM wrestler r WHERE r.wrestling_brand_id = ?1", nativeQuery = true)
    List<Wrestler> findWrestlerByBrandId(Long brandId);

    @Query(value = "SELECT * FROM wrestler r WHERE r.in_ring_name = ?1", nativeQuery = true)
    List<Wrestler> findWrestlerByInRingName(String ringName);

}
