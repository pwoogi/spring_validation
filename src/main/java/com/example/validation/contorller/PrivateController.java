package com.example.validation.contorller;

import com.example.validation.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Auth
@RestController
@RequestMapping("/api/private")
public class PrivateController {

    @GetMapping("/one")
    public String hell(){
        log.info("private controller");
        return "private hello";
    }
}
