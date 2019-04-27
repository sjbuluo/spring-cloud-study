package com.sun.health.apigetway;

import com.sun.health.apigetway.filter.AccessFilter;
import com.sun.health.apigetway.filter.ThrowExceptionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetwayApplication.class, args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	@Bean
    public ThrowExceptionFilter throwExceptionFilter() {
	    return new ThrowExceptionFilter();
    }

    @Bean
	@RefreshScope
	@ConfigurationProperties("zuul")
	@Primary
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}

}
