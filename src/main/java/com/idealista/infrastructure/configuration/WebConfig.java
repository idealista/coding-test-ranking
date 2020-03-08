package com.idealista.infrastructure.configuration;

import com.idealista.infrastructure.services.ads.converters.AdVOToPublicAdConverter;
import com.idealista.infrastructure.services.ads.converters.AdVOToQualityAdConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AdVOToPublicAdConverter());
        registry.addConverter(new AdVOToQualityAdConverter());
    }
}
