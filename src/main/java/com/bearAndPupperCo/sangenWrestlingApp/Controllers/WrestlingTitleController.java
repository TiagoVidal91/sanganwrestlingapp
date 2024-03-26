package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import com.bearAndPupperCo.sangenWrestlingApp.Services.WrestlingTitleSrv;
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
@RequestMapping("/teiai-api/wrestlingTitle")
public class WrestlingTitleController {

    private final Gson gson;
    private final WrestlingTitleSrv wrestlingTitleSrv;

    public WrestlingTitleController(Gson gson, WrestlingTitleSrv wrestlingTitleSrv) {
        this.gson = gson;
        this.wrestlingTitleSrv = wrestlingTitleSrv;
    }

    @PostMapping(value = "/addTitle")
    public ResponseEntity<String> addNewWrestlingBrand(@RequestBody String wrestlingTitleInfo){
        WrestlingTitle wrestlingTitle = gson.fromJson(wrestlingTitleInfo, WrestlingTitle.class);
        String wrestlingTitleMsg = gson.toJson(wrestlingTitleSrv.addWrestlingTitle(wrestlingTitle));
        return new ResponseEntity<String>(wrestlingTitleMsg, HttpStatus.OK);
    }

    @PostMapping(value = "/addAllTitles")
    public ResponseEntity<String> addNewWrestlingBrandList(@RequestBody String wrestlingTitleInfo){
        Type wrestlingTitlesListType = new TypeToken<ArrayList<WrestlingTitle>>(){}.getType();

        List<WrestlingTitle> wrestlingTitlesList = gson.fromJson(wrestlingTitleInfo, wrestlingTitlesListType);

        String wrestlingTitleMsg = gson.toJson(wrestlingTitleSrv.addWrestlingTitles(wrestlingTitlesList));

        return new ResponseEntity<String>(wrestlingTitleMsg, HttpStatus.OK);
    }

}
