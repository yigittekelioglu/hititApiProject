package org.example.hititapi;

import org.example.hititapi.service.HititApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class HititApiProjectApplication implements CommandLineRunner {

    private final HititApiService hititApiService;

    @Autowired
    public HititApiProjectApplication(HititApiService hititApiService) {
        this.hititApiService = hititApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HititApiProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Verilerin doğru çekilip çekilmediğini anlamak için print metodunu çağırıyorum kod ilk çalıştığında.
        hititApiService.printRepositoriesAndContributors();
    }
}
