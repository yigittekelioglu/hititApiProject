package org.example.hititapi.controller;
import org.example.hititapi.service.HititApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HititApiController {

    private final HititApiService hititApiService;

    @Autowired
    public HititApiController(HititApiService hititApiService) {
        this.hititApiService = hititApiService;
    }

    @GetMapping("/repositories")
    public String getRepositories() {

        return "Application has worked, data saved to database.";
    }
}

