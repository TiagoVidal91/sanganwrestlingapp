package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlingLockerSrv;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teiai-api/locker")
public class WrestlingLockerController {

    @Autowired
    Gson gson;

    @Autowired
    WrestlingLockerSrv wrestlingLockerSrv;

    @PostMapping(value = "/addLocker")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlingLockerInfo){
        LockerRoom lockerRoom = gson.fromJson(wrestlingLockerInfo, LockerRoom.class);
        String wrestlingLockerMsg = gson.toJson(wrestlingLockerSrv.addWrestlingLocker(lockerRoom));
        return new ResponseEntity<String>(wrestlingLockerMsg, HttpStatus.OK);
    }
    @PostMapping(value = "/addAllLockers")
    public ResponseEntity<String> addNewWrestlingBrandList(@RequestBody String wrestlingLockerInfo){
        Type wrestlingLockersListType = new TypeToken<ArrayList<LockerRoom>>(){}.getType();

        List<LockerRoom> wrestlingLockersList = gson.fromJson(wrestlingLockerInfo, wrestlingLockersListType);

        String wrestlingLockersMsg = gson.toJson(wrestlingLockerSrv.addWrestlingLockers(wrestlingLockersList));

        return new ResponseEntity<String>(wrestlingLockersMsg, HttpStatus.OK);
    }

}
