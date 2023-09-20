package com.seomoon.boundedContext.cafe.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom.cafe")
public class CafeConfigProperties {

    private Integer memberLimit;

}
