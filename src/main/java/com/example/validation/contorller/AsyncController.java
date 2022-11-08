package com.example.validation.contorller;

import com.example.validation.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/async")
@RequiredArgsConstructor
public class AsyncController {

    @Autowired
    private final AsyncService asyncService;

    @GetMapping("/hello")
    public CompletableFuture async(){
        log.info("completable future init");
        return asyncService.run();
    }
}
