package com.asPasa.testTask.configurations;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfig {
    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/testTaskDb", "postgres", "1234")
                .locations("classpath:db/migration/structure")
                .load();
    }
}