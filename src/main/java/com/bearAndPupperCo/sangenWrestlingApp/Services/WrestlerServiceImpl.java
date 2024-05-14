package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.ValidationUtils;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRESTLER_ALREADY_EXISTS_MSG;
import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRONG_PARAM_MSG;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    private final WrestlerRepo wrestlerRepo;
    private final ValidationUtils validationUtils;

    public WrestlerServiceImpl(WrestlerRepo wrestlerRepo, ValidationUtils validationUtils) {
        this.wrestlerRepo = wrestlerRepo;
        this.validationUtils = validationUtils;
    }

    @Override
    public SingleWrestlerDTO addNewWrestler(SingleWrestlerDTO wrestler) {
        if(!wrestlerRepo.findWrestlerByInRingName(wrestler.getWrestlerName()).isEmpty()){
            throw new WrestlerAlreadyExistsException(WRESTLER_ALREADY_EXISTS_MSG);
        }

        try {
            return wrestlerRepo.addNewWrestler(wrestler);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PaginatedResponse<WrestlerMainTableDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId,
                                                                            String orderBy, String orderDirection) {
        validationUtils.validateOrderDirection(orderDirection);

        List<WrestlerMainTableDTO> wrestlerList = wrestlerRepo.findWrestlerListByParams(page, size, brandId, lockerId,
                WrestlerMainTableEnum.getColumnName(orderBy), orderDirection);

        int totalWrestlers = wrestlerRepo.getTotalWrestlerCount(brandId, lockerId);
        int totalPages =  (int) Math.ceil((double) totalWrestlers / size);

        return new PaginatedResponse<>(wrestlerList, page, size, totalWrestlers, totalPages);
    }
}
