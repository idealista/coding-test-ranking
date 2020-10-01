package com.idealista.infrastructure.persistence.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan({"com.idealista.infrastructure.persistence"})
@Profile("in-memory-repository")
public class Config {
}
