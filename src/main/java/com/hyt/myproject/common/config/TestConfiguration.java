package com.hyt.myproject.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2018/7/17.
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class TestConfiguration {

    private String name;

    private Integer age;

    private String filePath;

}
