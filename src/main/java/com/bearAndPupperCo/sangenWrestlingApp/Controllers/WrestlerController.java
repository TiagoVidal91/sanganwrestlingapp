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

    private final Gson gson;

    private final WrestlerSrv wrestlerSrv;

    public WrestlerController(Gson gson, WrestlerSrv wrestlerSrv) {
        this.gson = gson;
        this.wrestlerSrv = wrestlerSrv;
    }

    @PostMapping(value = "/addWrestler")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlerInfo){
        Wrestler wrestler = gson.fromJson(wrestlerInfo, Wrestler.class);
        String wrestlerMsg = gson.toJson(wrestlerSrv.addNewWrestler(wrestler));
        return new ResponseEntity<>(wrestlerMsg, HttpStatus.OK);
    }
    @GetMapping(value = "/findAllWrestlersByParam")
    public ResponseEntity<String> findAllWrestlersByParams(
            @RequestParam(name = "pageNumber") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "lockerId", required = false) Integer lockerId){
        String wrestlerMsg = gson.toJson(wrestlerSrv.findAllWrestlersByParams(page, size, brandId, lockerId));
        return new ResponseEntity<>(wrestlerMsg, HttpStatus.OK);
    }
}
