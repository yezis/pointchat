package com.pointchat.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = BaseProperties.Base_Properties_prefix)
public class BaseProperties {
    public static final String Base_Properties_prefix = "baseproperties";

    private int port;
}
