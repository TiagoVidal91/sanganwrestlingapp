package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.DTO.SingleWrestlerDTO;
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

    private final WrestlerSrv wrestlerSrv;

    public WrestlerController(WrestlerSrv wrestlerSrv) {
        this.wrestlerSrv = wrestlerSrv;
    }

    @PostMapping(value = "/addNewWrestler")
    public ResponseEntity<?> addNewWrestlingBrand(@RequestBody SingleWrestlerDTO wrestlerDTO){
        SingleWrestlerDTO savedWrestler = wrestlerSrv.addNewWrestler(wrestlerDTO);
        return new ResponseEntity<>(savedWrestler, HttpStatus.CREATED);
    }
    @GetMapping(value = "/findAllWrestlersByParam")
    public ResponseEntity<?> findAllWrestlersByParams(
            @RequestParam(name = "pageNumber") int page,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "lockerId", required = false) Integer lockerId,
            @RequestParam(name = "order") String order,
            @RequestParam(name = "orderDirection") String orderDirection
    ){
        PaginatedResponse<WrestlerMainTableDTO> wrestlerListPage =
                wrestlerSrv.findAllWrestlersByParams(page, size, brandId, lockerId, order, orderDirection);

        return new ResponseEntity<>(wrestlerListPage, HttpStatus.OK);
    }
}
