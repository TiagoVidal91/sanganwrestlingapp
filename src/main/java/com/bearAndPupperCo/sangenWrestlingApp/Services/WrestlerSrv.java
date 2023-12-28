package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WrestlerSrv {

    Wrestler addNewWrestler(Wrestler wrestler);
    PaginatedResponse<WrestlerDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId);

}
