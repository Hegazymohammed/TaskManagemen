package com.example.taskmanagement.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.google")
@Data
public class GoogleConfiguration {
    private String projectId;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String tokenUri;


}