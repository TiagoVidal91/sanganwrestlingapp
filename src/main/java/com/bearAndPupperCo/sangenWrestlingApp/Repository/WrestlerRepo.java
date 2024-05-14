package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WrestlerRepo {

    List<Wrestler> findWrestlerByInRingName(String ringName);

    SingleWrestlerDTO addNewWrestler(SingleWrestlerDTO wrestlerDTO);

    List<WrestlerMainTableDTO> findWrestlerListByParams(Integer page, Integer pageSize, Integer brandId, Integer lockerRoomId,
                                                String orderBy, String orderDirection);
    int getTotalWrestlerCount(Integer brandId, Integer lockerRoomId);


}
