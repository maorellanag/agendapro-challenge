package com.agendapro.challenge.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "basic.auth")
@Data
public class BasicAuthProperties {

    private String username;
    private String password;

}
