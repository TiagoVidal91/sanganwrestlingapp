package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlerSrv;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teiai-api/wrestler")
public class WrestlerController {

    @Autowired
    Gson gson;

    @Autowired
    WrestlerSrv wrestlerSrv;

    @PostMapping(value = "/addWrestler")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlerInfo){
        Wrestler wrestler = gson.fromJson(wrestlerInfo, Wrestler.class);
        String wrestlerMsg = gson.toJson(wrestlerSrv.addNewWrestler(wrestler));
        return new ResponseEntity<String>(wrestlerMsg, HttpStatus.OK);
    }




}
