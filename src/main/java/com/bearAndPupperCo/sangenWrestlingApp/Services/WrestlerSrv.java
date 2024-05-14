package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;

public interface WrestlerSrv {

    SingleWrestlerDTO addNewWrestler(SingleWrestlerDTO wrestler);
    PaginatedResponse<WrestlerMainTableDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId,
                                                                     String orderBy, String orderDirection);

}
