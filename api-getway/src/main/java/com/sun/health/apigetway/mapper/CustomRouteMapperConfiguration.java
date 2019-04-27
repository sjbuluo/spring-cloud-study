package com.sun.health.apigetway.mapper;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteMapperConfiguration {

    @Bean
    public PatternServiceRouteMapper routeMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(service$)", "${name}");
    }

}
