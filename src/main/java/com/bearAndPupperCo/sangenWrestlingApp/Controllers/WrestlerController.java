package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlerSrv;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/findAllWrestlers")
    public ResponseEntity<String> findAllWrestlers(){
        List<WrestlerDTO> wrestlerList = wrestlerSrv.findAllWrestlers();
        String wrestlerMsg = gson.toJson(wrestlerList);
        return new ResponseEntity<String>(wrestlerMsg, HttpStatus.OK);
    }

    @GetMapping(value = "/findWrestlerByLocker/{lockerId}")
    public ResponseEntity<String> findWrestlerByLocker(@RequestParam long lockerId){
        String wrestlerMsg = gson.toJson(wrestlerSrv.findWrestlerByLocker(lockerId));
        return new ResponseEntity<String>(wrestlerMsg, HttpStatus.OK);
    }

    @GetMapping(value = "/findWrestlerByBrand/{brandId}")
    public ResponseEntity<String> findWrestlerByBrand(@RequestParam long brandId){
        String wrestlerMsg = gson.toJson(wrestlerSrv.findWrestlerByBrand(brandId));
        return new ResponseEntity<String>(wrestlerMsg, HttpStatus.OK);
    }




}
