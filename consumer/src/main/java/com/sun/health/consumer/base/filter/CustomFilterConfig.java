package com.sun.health.consumer.base.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class CustomFilterConfig {

    @Bean
    public FilterRegistrationBean<HystrixRequestContextServletFilter> addHystrixRequestContextServletFilter() {
        FilterRegistrationBean<HystrixRequestContextServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new HystrixRequestContextServletFilter());
        filterRegistrationBean.setName("hystrixRequestContextServletFilter");
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
