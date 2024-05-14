package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.WrestlerMainTableDTO;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.Wrestler;
import com.bearAndPupperCo.sangenWrestlingApp.Pagination.PaginatedResponse;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlerSrv;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teiai-api/wrestler")
public class WrestlerController {

    private final Gson gson;
    private final WrestlerSrv wrestlerSrv;

    public WrestlerController(Gson gson, WrestlerSrv wrestlerSrv) {
        this.gson = gson;
        this.wrestlerSrv = wrestlerSrv;
    }

    @PostMapping(value = "/addNewWrestler")
    public ResponseEntity<?> addNewWrestlingBrand(@RequestBody Wrestler wrestler){
        Wrestler savedWrestler = wrestlerSrv.addNewWrestler(wrestler);
        return new ResponseEntity<>(wrestler, HttpStatus.CREATED);
    }
    @GetMapping(value = "/findAllWrestlersByParam")
    public ResponseEntity<String> findAllWrestlersByParams(
            @RequestParam(name = "pageNumber") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "lockerId", required = false) Integer lockerId,
            @RequestParam(name = "order", required = true) String order,
            @RequestParam(name = "orderDirection", required = true) String orderDirection
    ){
        PaginatedResponse<WrestlerMainTableDTO> wrestlerListPage =
                wrestlerSrv.findAllWrestlersByParams(page, size, brandId, lockerId, order, orderDirection);

        String response = gson.toJson(wrestlerListPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
