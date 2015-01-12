package com.boluogan.domain.domaintool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by boluogan.com on 12/30/14.
 */
@Configuration
public class WebappConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //System.out.println("*********************************************");
        configurer.favorPathExtension(false).favorParameter(true);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseSuffixPatternMatch(true)
                .setUseTrailingSlashMatch(false)
                .setUseRegisteredSuffixPatternMatch(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*String datasDiretoryPath = MyIOUtils.getDatasDirectoryName();
        if (!registry.hasMappingForPattern("/datas*//**")) {
            registry.addResourceHandler("/datas*//**").addResourceLocations(datasDiretoryPath);
        }*/
        /*if (!registry.hasMappingForPattern("*//**")) {
            registry.addResourceHandler("*//**").addResourceLocations(
                    RESOURCE_LOCATIONS);
        }*/
    }


}
