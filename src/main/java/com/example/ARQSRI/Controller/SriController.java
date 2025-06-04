package com.example.ARQSRI.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SriController {

    @GetMapping
    public String testEndpoint() {
        return "La aplicación está funcionando correctamente!";
    }
}
