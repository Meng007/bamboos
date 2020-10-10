package com.sdz.love.bamboos.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 13557
 */
@Configuration
@ComponentScan(basePackages = "com.sdz.love.bamboos.controller")
public class BeanConfig {


}
