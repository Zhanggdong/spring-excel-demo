package com.vivo.soft.excel.springexceldemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 16:26
 * @Description TODO
 * @Version 2.0.0
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
