package com.example.validation.contorller;

import com.example.validation.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api-v3")
public class FilterV2Controller {

    @PostMapping("/temp")
    public Member member(@RequestBody Member member){

        log.info("Temp :{}", member);
        return member;
    }
}
