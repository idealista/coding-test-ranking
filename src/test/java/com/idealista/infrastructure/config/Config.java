package com.idealista.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan({"com.idealista.infrastructure.persistence"})
@Profile("in-memory-repository")
public class Config {
}
