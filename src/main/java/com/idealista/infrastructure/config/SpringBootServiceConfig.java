package com.idealista.infrastructure.config;

import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.service.impl.AdServiceImpl;
import org.springframework.context.annotation.Bean;

public class SpringBootServiceConfig {

  @Bean
  public AdService adService(AdRepository adRepository) {
    return new AdServiceImpl(adRepository);
  }

}
