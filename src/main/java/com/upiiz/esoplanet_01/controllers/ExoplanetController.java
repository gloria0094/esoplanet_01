package com.upiiz.esoplanet_01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exoplanets")
//@CrossOrigin(origins = "*")
public class ExoplanetController {

    private final RestTemplate restTemplate = new RestTemplate();
    //private final String FAST_API_URL = "https://gloriaitzel0609-api-ia-python.hf.space/predict";
    private final String FAST_API_URL  = "https://gloriaitzel0609-api-ia-python.hf.space/predict";

    @PostMapping("/classify")
    public ResponseEntity<?> classifyExoplanet(@RequestBody Map<String, Object> datos) {
        try {
            ResponseEntity<Object> response = restTemplate.postForEntity(FAST_API_URL, datos, Object.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            // Esto imprimirá el error real en los logs de Railway por si algo más se rompe
            System.out.println("Error detallado en la llamada a la IA: " + e.getMessage());
            String jsonError = "{\"error\": \"Error al comunicarse con el modelo de IA\"}";
            return ResponseEntity.status(500).body(jsonError);
        }
    }
}
