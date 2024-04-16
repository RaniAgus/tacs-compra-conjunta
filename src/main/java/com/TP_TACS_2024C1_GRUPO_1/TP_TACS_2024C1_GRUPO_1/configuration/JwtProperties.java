package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private long expirationTime;
}
