package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;

import java.util.List;

public interface WrestlerSrv {

    Wrestler addNewWrestler(Wrestler wrestler);
    List<WrestlerDTO> findAllWrestlers();
    List<Wrestler> findWrestlerByLocker(Long lockerId);

    List<Wrestler> findWrestlerByBrand(Long brandId);

}
