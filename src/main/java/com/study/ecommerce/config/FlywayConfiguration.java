package com.study.ecommerce.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    @Autowired
    private DataSource dataSource;
    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .outOfOrder(false).load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }
}
