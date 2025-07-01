package org.example.controller;

import org.example.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    private final ExchangeService service;

    public ExchangeController(ExchangeService service) {
        this.service = service;
    }

    @GetMapping("/convert")
    public ResponseEntity<Double> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return ResponseEntity.ok(service.convert(from, to, amount));
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestParam String from, @RequestParam String to) {
        service.fetchAndSaveRate(from, to);
        return ResponseEntity.ok("Exchange rate refreshed");
    }
}
