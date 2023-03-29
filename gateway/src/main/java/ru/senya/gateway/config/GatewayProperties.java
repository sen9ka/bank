package ru.senya.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gateway")
@Data
public class GatewayProperties {

    private String applicationLink;
    private String offersLink;
    private String calculationLink;
    private String dealDocumentsLink;
    private String dealSendDomain;
    private String dealSignDomain;
    private String dealCodeDomain;

}
