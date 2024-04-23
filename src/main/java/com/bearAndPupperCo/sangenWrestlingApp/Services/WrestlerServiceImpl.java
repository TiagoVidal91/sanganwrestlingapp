package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.ValidationUtils;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRONG_PARAM_MSG;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    private final WrestlerRepo wrestlerRepo;

    private final ModelMapper modelMapper;

    public WrestlerServiceImpl(WrestlerRepo wrestlerRepo, ModelMapper modelMapper) {
        this.wrestlerRepo = wrestlerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Wrestler addNewWrestler(Wrestler wrestler) {
        verifyIfWrestlerExists(wrestler);
        //setWrestlingTitleLockerRoom(wrestler);
        //return wrestlerRepo.save(wrestler);
        return null;
    }

    @Override
    public PaginatedResponse<WrestlerMainTableDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId,
                                                                            String orderBy, String orderDirection) {


        if (!ValidationUtils.validateOrderDirection(orderDirection)){
            throw new WrongParamException(WRONG_PARAM_MSG);
        }

        String orderByColumn = WrestlerMainTableEnum.getColumnName(orderBy);

        Pageable pageable = PageRequest.of(page, size);

        List<WrestlerMainTableDTO> wrestlerList = wrestlerRepo.findWrestlerListByParams(pageable, brandId, lockerId,
                orderByColumn, orderDirection);

        int totalWrestlers = wrestlerRepo.getTotalWrestlerCount(brandId, lockerId);

        int totalPages =  (int) Math.ceil((double) totalWrestlers / pageable.getPageSize());

        return new PaginatedResponse<>(
                wrestlerList,
                page,
                size,
                totalWrestlers,
                totalPages
        );
    }

    private void verifyIfWrestlerExists(Wrestler wrestler){
        if(!wrestlerRepo.findWrestlerByInRingName(wrestler.getInRingName()).isEmpty()){
            throw new WrestlerAlreadyExistsException("The wrestler already exists on the Database",
                    HttpStatus.CONFLICT.name(),
                    "It seems like the wrestler " +
                    "you tried to add was already on our system. Please make sure to add a new wrestler.");
        }
    }
}
