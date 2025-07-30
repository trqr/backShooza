package com.shooza.demo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(DataSource dataSource) {
        return args -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("data.sql"));
            populator.execute(dataSource);
        };
    }
}

