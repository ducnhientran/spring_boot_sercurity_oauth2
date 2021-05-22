package com.study.ecommerce.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager",
        basePackages = {"com.study.ecommerce.repository"})
public class DatabaseConfig {


    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariConfig getHikariConfig(){
        return new HikariConfig();
    }

    @Primary
    @Bean("dataSource")
    public DataSource dataSource() {
        return new HikariDataSource(getHikariConfig());
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    //@DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                   @Qualifier("dataSource") DataSource dataSource ) {
       return builder.dataSource(dataSource)
        .packages("com.study.ecommerce.entity")
        .persistenceUnit("ecommerce")
        .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

