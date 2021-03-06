package com.www;

import com.www.service.CountryService;
import com.www.service.LoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

@EnableCircuitBreaker
@SpringBootApplication
public class WWWApplication
{
    private static final Logger log = LoggerFactory.getLogger(WWWApplication.class);

    private LoaderService loaderService;

    @Autowired
    public WWWApplication(LoaderService loaderService)
    {
        this.loaderService = loaderService;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(WWWApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CountryService service)
    {
        log.info("Running setup");
        return args -> service.save(loaderService.getFromUrl());
    }
}
