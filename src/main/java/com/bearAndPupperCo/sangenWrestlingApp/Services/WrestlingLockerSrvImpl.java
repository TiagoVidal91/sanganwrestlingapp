package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingLockerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrestlingLockerSrvImpl implements WrestlingLockerSrv{

    private final WrestlingLockerRepo wrestlingLockerRepo;

    public WrestlingLockerSrvImpl(WrestlingLockerRepo wrestlingLockerRepo) {
        this.wrestlingLockerRepo = wrestlingLockerRepo;
    }

    @Override
    public LockerRoom addWrestlingLocker(LockerRoom lockerRoom) {
        return wrestlingLockerRepo.save(lockerRoom);
    }

    @Override
    public List<LockerRoom> addWrestlingLockers(List<LockerRoom> wrestlingLockers) {
        return wrestlingLockerRepo.saveAll(wrestlingLockers);
    }
}
