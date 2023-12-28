package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingTitleRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    @Autowired
    WrestlerRepo wrestlerRepo;

    @Autowired
    WrestlingTitleRepo wrestlingTitleRepo;

    @Autowired
    ModelMapper modelMapper;

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
    public List<WrestlerDTO> findAllWrestlers() {
        List<WrestlerDTO> wrestlerDTOList = new ArrayList<>();

        TypeMap<Wrestler, WrestlerDTO> propertyMapper = modelMapper.createTypeMap(Wrestler.class, WrestlerDTO.class);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setPreferNestedProperties(false);
        defineWrestlerMapper(propertyMapper);

        for (Wrestler wrestler: wrestlerRepo.findAll()) {
            WrestlerDTO wrestlerDTO = modelMapper.map(wrestler, WrestlerDTO.class);
            wrestlerDTOList.add(wrestlerDTO);
        }

        return wrestlerDTOList;

    }

    @Override
    public List<Wrestler> findWrestlerByLocker(Long lockerId) {
        return wrestlerRepo.findWrestlerByWrestlingLockerRoomId(lockerId);
    }

    @Override
    public List<Wrestler> findWrestlerByBrand(Long brandId) {
        return wrestlerRepo.findWrestlerByBrandId(brandId);
    }

    //This code was created to solve a bug that was caused by something different, so it is not being used, however
    //it might in the future
    private void setWrestlingTitleLockerRoom(Wrestler wrestler) {
        for (WrestlingTitle wrestlingTitle: wrestler.getWrestlingTitleList()) {
            wrestlingTitle.setLockerRoom(wrestlingTitleRepo
                    .findWrestlingTitleByWrestlingTitleId(wrestlingTitle.getWrestlingTitleId()).getLockerRoom());
        }
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
