package com.ewmw.addr.config.loaders;

import com.ewmw.addr.config.services.GarConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GarConfigLoader {
    @Bean
    public GarConfig getGarConfig() {
        return new GarConfig();
    }
}
