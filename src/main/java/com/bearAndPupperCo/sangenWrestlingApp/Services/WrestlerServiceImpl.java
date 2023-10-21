package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlerRepo;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WrestlerServiceImpl implements WrestlerSrv{

    @Autowired
    WrestlerRepo wrestlerRepo;

    @Autowired
    WrestlingTitleRepo wrestlingTitleRepo;

    @Override
    public Wrestler addNewWrestler(Wrestler wrestler) {
        //setWrestlingTitleLockerRoom(wrestler);
        return wrestlerRepo.save(wrestler);
    }

    //This code was created to solve a bug that was caused by something different, so it is not being used, however
    //it might in the future
    private void setWrestlingTitleLockerRoom(Wrestler wrestler) {
        for (WrestlingTitle wrestlingTitle: wrestler.getWrestlingTitleList()) {
            wrestlingTitle.setLockerRoom(wrestlingTitleRepo
                    .findWrestlingTitleByWrestlingTitleId(wrestlingTitle.getWrestlingTitleId()).getLockerRoom());
        }
    }
}
