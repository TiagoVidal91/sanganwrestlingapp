package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.Base64Utils;
import com.bearAndPupperCo.sangenWrestlingApp.APIUtils.ValidationUtils;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlerMainTableEnum;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.WRESTLER_ALREADY_EXISTS_MSG;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    private static final Logger logger = LoggerFactory.getLogger(WrestlerServiceImpl.class);
    private final WrestlerRepo wrestlerRepo;
    private final ValidationUtils validationUtils;
    private final Base64Utils base64Utils;

    public WrestlerServiceImpl(WrestlerRepo wrestlerRepo, ValidationUtils validationUtils, Base64Utils base64Utils) {
        this.wrestlerRepo = wrestlerRepo;
        this.validationUtils = validationUtils;
        this.base64Utils = base64Utils;
    }

    @Override
    public SingleWrestlerDTO addNewWrestler(SingleWrestlerDTO wrestler) throws IOException {

        /*if(!wrestlerRepo.findWrestlerByInRingName(wrestler.getWrestlerName()).isEmpty()){
            throw new WrestlerAlreadyExistsException(WRESTLER_ALREADY_EXISTS_MSG);
        }*/

        if(!wrestler.getWrestlerPic().isEmpty() && !wrestler.getWrestlerPic().isBlank()){
            base64Utils.encodeImage(wrestler);
        }

        try {
            return wrestlerRepo.addNewWrestler(wrestler);
        }catch (Exception e){
            logger.error("Failed to save new wrestler", e);
            throw e;
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
