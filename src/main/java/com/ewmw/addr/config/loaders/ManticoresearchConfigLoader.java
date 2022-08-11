package com.ewmw.addr.config.loaders;

import com.ewmw.addr.config.services.ManticoresearchConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManticoresearchConfigLoader {

    @Bean
    public ManticoresearchConfig getManticoresearchConfig() {
        return new ManticoresearchConfig();
    }
}
