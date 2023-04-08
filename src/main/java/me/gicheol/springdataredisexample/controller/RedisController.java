package me.gicheol.springdataredisexample.controller;

import me.gicheol.springdataredisexample.dto.Request;
import me.gicheol.springdataredisexample.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class RedisController {

    private static final Logger log = LoggerFactory.getLogger(RedisController.class);
    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }


    @GetMapping("/")
    public String web() {
        return "web";
    }

    @PostMapping("/")
    public ResponseEntity<?> restApi(@RequestBody Request request) {
        request.setData(request.getData() + "TEST");
        String key = UUID.randomUUID().toString();
        String data = request.getData();

        redisService.save(key, request);

        log.info("key={}, value={}", key, data);
        Object result = redisService.get(key);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> viewValue(@PathVariable String key) {
        Object result = redisService.get(key);
        return ResponseEntity.ok(result);
    }


}
