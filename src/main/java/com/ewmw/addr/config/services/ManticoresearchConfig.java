package com.ewmw.addr.config.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class ManticoresearchConfig {
    @Getter @Setter
    private String baseUrl = "http://localhost:8181";
}
