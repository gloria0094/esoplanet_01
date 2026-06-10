package com.upiiz.esoplanet_01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/exoplanets")
//@CrossOrigin(origins = "*")
public class ExoplanetController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FAST_API_URL = "http://localhost:8000/predict";

    @PostMapping("/classify")
    public ResponseEntity<?> classifyExoplanet(@RequestBody Object requestData) {
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(FAST_API_URL, requestData, Object.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            String jsonError = "{\"error\": \"Error al comunicarse con el modelo de IA\"}";
            return ResponseEntity.status(500).body(jsonError);
        }
    }
}