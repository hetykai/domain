package com.boluogan.domain.domaintool.config;

/**
 * Created by boluogan.com on 1/12/15.
 */
import com.boluogan.domain.domaintool.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
