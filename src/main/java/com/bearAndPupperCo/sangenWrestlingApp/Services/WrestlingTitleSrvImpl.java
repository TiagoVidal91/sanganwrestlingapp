package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrestlingTitleSrvImpl implements WrestlingTitleSrv{

    @Autowired
    WrestlingTitleRepo wrestlingTitleRepo;

    @Override
    public WrestlingTitle addWrestlingTitle(WrestlingTitle wrestlingTitle) {
        return wrestlingTitleRepo.save(wrestlingTitle);
    }

    @Override
    public List<WrestlingTitle> addWrestlingTitles(List<WrestlingTitle> wrestlingTitles) {
        return wrestlingTitleRepo.saveAll(wrestlingTitles);
    }
}
