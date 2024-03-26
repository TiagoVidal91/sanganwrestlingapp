package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Repository.WrestlingBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrestlingBrandSrvImpl implements WrestlingBrandSrv{


    private final WrestlingBrandRepo wrestlingBrandRepo;

    public WrestlingBrandSrvImpl(WrestlingBrandRepo wrestlingBrandRepo) {
        this.wrestlingBrandRepo = wrestlingBrandRepo;
    }

    @Override
    public WrestlingBrand addWrestlingBrand(WrestlingBrand wrestlingBrand) {
        return wrestlingBrandRepo.save(wrestlingBrand);
    }

    @Override
    public List<WrestlingBrand> addWrestlingBrands(List<WrestlingBrand> wrestlingBrands) {
        return wrestlingBrandRepo.saveAll(wrestlingBrands);
    }
}
