package sportsRentals.springboot.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.module.SimpleModule;

import sportsRentals.json.sportsRentalsPersistence;

/**
 * Configures and launches the springboot-application.
 */
@SpringBootApplication
public class SportsRentalsApplication {

    /**
     * Creates a Jackson module for sportsRentalsPersistence. Registers the return value as a bean.
     * @return A SimpleModule for sportsRentalsPersistence
     */
    @Bean
    public SimpleModule objectMapperModule() {
        return sportsRentalsPersistence.createJacksonModule();
    }

    /**
     * Launches the Spring Boot application.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SportsRentalsApplication.class, args);
    }
}
