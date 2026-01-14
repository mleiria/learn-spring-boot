package com.in28minutes.springboot.learn_spring_boot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {

    private final CurrencyServiceConfiguration configuration;

    public CurrencyConfigurationController(final CurrencyServiceConfiguration configuration) {
        this.configuration = configuration;
    }

    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses() {
        return configuration;
    }
}
