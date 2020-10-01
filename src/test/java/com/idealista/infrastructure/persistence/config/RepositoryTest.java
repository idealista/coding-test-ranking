package com.idealista.infrastructure.persistence.config;

import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ContextConfiguration(classes = {Config.class})
public @interface RepositoryTest {
}
