package com.mars.ums.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource({"classpath:mysql.properties"})
@EntityScan("com.mars")
@EnableJpaRepositories("com.mars")
public class JpaConfig {
}
