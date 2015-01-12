package com.boluogan.domain.domaintool;

import com.boluogan.domain.domaintool.config.WebappConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by boluogan.com on 12/16/14.
 */

@SpringBootApplication
@Import(WebappConfig.class)
public class Application   {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }





}
