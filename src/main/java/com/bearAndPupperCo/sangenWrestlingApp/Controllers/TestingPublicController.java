package com.bearAndPupperCo.sangenWrestlingApp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teiai-api/test")
public class TestingPublicController {

    @GetMapping(value = "/test-server-response")
    public ResponseEntity<String> testServerResponse() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
