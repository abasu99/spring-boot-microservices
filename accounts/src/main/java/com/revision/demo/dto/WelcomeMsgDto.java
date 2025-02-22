package com.revision.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record WelcomeMsgDto(String message) {
}
