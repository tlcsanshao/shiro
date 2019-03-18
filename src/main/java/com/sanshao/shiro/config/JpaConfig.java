package com.sanshao.shiro.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:mysql.properties")
@EntityScan("com.sanshao")
@EnableJpaRepositories("com.sanshao")
public class JpaConfig {
}
