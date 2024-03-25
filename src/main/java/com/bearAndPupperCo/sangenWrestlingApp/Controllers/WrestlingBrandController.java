package com.bearAndPupperCo.sangenWrestlingApp.Controllers;


import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlingBrandSrv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teiai-api/brand")
public class WrestlingBrandController {

    private final Gson gson;

    private final WrestlingBrandSrv wrestlingBrandSrv;

    public WrestlingBrandController(Gson gson, WrestlingBrandSrv wrestlingBrandSrv) {
        this.gson = gson;
        this.wrestlingBrandSrv = wrestlingBrandSrv;
    }

    @PostMapping(value = "/addBrand")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlingBrandInfo){
        WrestlingBrand wrestlingBrand = gson.fromJson(wrestlingBrandInfo,WrestlingBrand.class);
        String wrestlingBrandmsg = gson.toJson(wrestlingBrandSrv.addWrestlingBrand(wrestlingBrand));
        return new ResponseEntity<String>(wrestlingBrandmsg, HttpStatus.OK);
    }
    @PostMapping(value = "/addAllBrand")
    public ResponseEntity<String> addNewWrestlingBrandList(@RequestBody String wrestlingBrandsInfo){
        Type wrestlingBrandsListType = new TypeToken<ArrayList<WrestlingBrand>>(){}.getType();

        List<WrestlingBrand> wrestlingBrandsList = gson.fromJson(wrestlingBrandsInfo, wrestlingBrandsListType);

        String wrestlingBrandmsg = gson.toJson(wrestlingBrandSrv.addWrestlingBrands(wrestlingBrandsList));

        return new ResponseEntity<String>(wrestlingBrandmsg, HttpStatus.OK);
    }



}
