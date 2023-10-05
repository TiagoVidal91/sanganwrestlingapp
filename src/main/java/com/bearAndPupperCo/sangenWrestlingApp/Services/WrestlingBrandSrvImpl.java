package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WrestlingBrandSrvImpl implements WrestlingBrandSrv{

    @Autowired
    WrestlingBrandRepo wrestlingBrandRepo;

    @Override
    public WrestlingBrand addWrestlingBrand(WrestlingBrand wrestlingBrand) {
        return wrestlingBrandRepo.save(wrestlingBrand);
    }
}
