package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WrestlerRepo {

    //@Query(value = "SELECT * FROM wrestler r WHERE r.in_ring_name = ?1", nativeQuery = true)
    List<Wrestler> findWrestlerByInRingName(String ringName);

    /*@Query(value = "SELECT * FROM wrestler w " +
            "WHERE (:brandId IS NULL OR w.wrestling_brand_Id = :brandId) " +
            "AND (:lockerRoomId IS NULL OR w.wrestling_locker_room_id = :lockerRoomId)", nativeQuery = true)*/
    List<WrestlerMainTableDTO> findWrestlerListByParams(Integer page, Integer pageSize, Integer brandId, Integer lockerRoomId,
                                                String orderBy, String orderDirection);
    int getTotalWrestlerCount(Integer brandId, Integer lockerRoomId);


}
