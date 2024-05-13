package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.ValidationUtils;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRONG_PARAM_MSG;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    private final WrestlerRepo wrestlerRepo;
    private final ValidationUtils validationUtils;

    public WrestlerServiceImpl(WrestlerRepo wrestlerRepo, ValidationUtils validationUtils) {
        this.wrestlerRepo = wrestlerRepo;
        this.validationUtils = validationUtils;
    }
    //TODO - Change to adapt to JDBI instead of JPA
    @Override
    public Wrestler addNewWrestler(Wrestler wrestler) {
        //verifyIfWrestlerExists(wrestler);
        //setWrestlingTitleLockerRoom(wrestler);
        //return wrestlerRepo.save(wrestler);
        return null;
    }

    @Override
    public PaginatedResponse<WrestlerMainTableDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId,
                                                                            String orderBy, String orderDirection) {
        String orderByColumn;

        if (!validationUtils.validateOrderDirection(orderDirection)){
            throw new WrongParamException(WRONG_PARAM_MSG);
        }

        try {
            orderByColumn = WrestlerMainTableEnum.getColumnName(orderBy);
        } catch (Exception e){
            throw new WrongParamException(WRONG_PARAM_MSG);
        }

        List<WrestlerMainTableDTO> wrestlerList = wrestlerRepo.findWrestlerListByParams(page, size, brandId, lockerId,
                orderByColumn, orderDirection);

        int totalWrestlers = wrestlerRepo.getTotalWrestlerCount(brandId, lockerId);
        int totalPages =  (int) Math.ceil((double) totalWrestlers / size);

        return new PaginatedResponse<>(wrestlerList, page, size, totalWrestlers, totalPages);
    }

    //TODO - Change to adapt to JDBI instead of JPA
    /*private void verifyIfWrestlerExists(Wrestler wrestler){
        if(!wrestlerRepo.findWrestlerByInRingName(wrestler.getInRingName()).isEmpty()){
            throw new WrestlerAlreadyExistsException("The wrestler already exists on the Database",
                    HttpStatus.CONFLICT.name(),
                    "It seems like the wrestler " +
                    "you tried to add was already on our system. Please make sure to add a new wrestler.");
        }
    }*/
}
