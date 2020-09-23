package com.idealista.application.config;

import com.idealista.application.converter.AdToPublicAdResponseConverter;
import com.idealista.application.converter.AdToQualityAdResponseConverter;
import com.idealista.application.service.picture.PictureService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PictureService pictureService;

    public WebConfig(PictureService repository) {
        this.pictureService = repository;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AdToPublicAdResponseConverter(pictureService));
        registry.addConverter(new AdToQualityAdResponseConverter(pictureService));
    }

}
