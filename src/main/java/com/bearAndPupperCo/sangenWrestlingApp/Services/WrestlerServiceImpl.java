package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingTitleRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    @Autowired
    WrestlerRepo wrestlerRepo;

    @Autowired
    WrestlingTitleRepo wrestlingTitleRepo;

    @Autowired
    ModelMapper modelMapper;

    public WrestlerServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        initializeTypeMap();
    }

    private void initializeTypeMap() {
        TypeMap<Wrestler, WrestlerDTO> propertyMapper = modelMapper.createTypeMap(Wrestler.class, WrestlerDTO.class);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setPreferNestedProperties(false);
        defineWrestlerMapper(propertyMapper);
    }

    @Override
    public Wrestler addNewWrestler(Wrestler wrestler) {
        if(!wrestlerRepo.findWrestlerByInRingName(wrestler.getInRingName()).isEmpty()){
            throw new WrestlerAlreadyExistsException("The wrestler already exists on the Database", HttpStatus.CONFLICT.name(),
                    "It seems like the wrestler " +
                    "you tried to add was already on our system. Please make sure to add a new wrestler.");
        }
        //setWrestlingTitleLockerRoom(wrestler);
        return wrestlerRepo.save(wrestler);
    }

    @Override
    public PaginatedResponse<WrestlerDTO> findAllWrestlersByParams(int page, int size, Integer brandId, Integer lockerId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Wrestler> wrestlersPage = wrestlerRepo.findDataByParams(pageable, brandId, lockerId);

        List<WrestlerDTO> wrestlerDTOList = wrestlersPage
                .stream()
                .map(wrestler -> modelMapper.map(wrestler, WrestlerDTO.class))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                wrestlerDTOList,
                wrestlersPage.getNumber(),
                wrestlersPage.getSize(),
                wrestlersPage.getTotalElements(),
                wrestlersPage.getTotalPages()
        );
    }

    private void defineWrestlerMapper(TypeMap<Wrestler, WrestlerDTO> propertyMapper) {
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getWrestlingLockerRoom().getWrestlingLockerRoomId(), WrestlerDTO::setLockerId)
        ).addMappings(
                mapper -> mapper.map(src -> src.getWrestlingBrand().getWrestlingBrandId(), WrestlerDTO::setBrandId)
        ).addMappings(
                    mapper -> mapper.using(ctx -> {
                        List<?> matchVictories = ((Wrestler) ctx.getSource()).getMatchVictories();
                        return matchVictories != null ? matchVictories.size() : 0;
                    }).map(src -> src, WrestlerDTO::setNumberOfWins)
        ).addMappings(
                    mapper -> mapper.using(ctx -> {
                        List<?> matchLosses = ((Wrestler) ctx.getSource()).getMatchLosses();
                        return matchLosses != null ? matchLosses.size() : 0;
                    }).map(src -> src, WrestlerDTO::setNumberOfLosses)
        ).addMappings(
                mapper -> mapper.using(ctx -> {
                    List<?> matchNumber = ((Wrestler) ctx.getSource()).getWrestlingMatchesList();
                    return matchNumber != null ? matchNumber.size() : 0;
                }).map(src -> src, WrestlerDTO::setNumberOfMatches)
        ).addMappings(
                mapper -> mapper.using(ctx -> {
                    List<?> matchNumber = ((Wrestler) ctx.getSource()).getWrestlingMatchesList();
                    List<?> matchVictories = ((Wrestler) ctx.getSource()).getMatchVictories();
                    return matchNumber.size() != 0 && matchVictories.size() != 0 ? ((matchVictories.size() / matchNumber.size()) * 100) : 0;
                }).map(src -> src, WrestlerDTO::setPercentageOfWins)
        );
    }
}
