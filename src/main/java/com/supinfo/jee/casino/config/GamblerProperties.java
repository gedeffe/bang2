package com.supinfo.jee.casino.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gambler")
@Getter
@Setter
public class GamblerProperties {
    private boolean enabled = true;
}
