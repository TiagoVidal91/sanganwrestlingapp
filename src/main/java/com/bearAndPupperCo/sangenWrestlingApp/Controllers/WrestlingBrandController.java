package com.bearAndPupperCo.sangenWrestlingApp.Controllers;


import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlingBrandSrv;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
@RequestMapping("/teiai-api/brand")
public class WrestlingBrandController {

    @Autowired
    Gson gson;

    @Autowired
    WrestlingBrandSrv wrestlingBrandSrv;

    @PostMapping(value = "/addBrand")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlingBrandInfo){
        WrestlingBrand wrestlingBrand = gson.fromJson(wrestlingBrandInfo,WrestlingBrand.class);
        String wrestlingBrandmsg = gson.toJson(wrestlingBrandSrv.addWrestlingBrand(wrestlingBrand));
        return new ResponseEntity<String>(wrestlingBrandmsg, HttpStatus.OK);
    }


}
