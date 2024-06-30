package com.example.moviestar_p.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocOpenapiConfig {
    @Bean
    public GroupedOpenApi restApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/api/**") // 경로에 api가 포함되어 있는 컨트롤러는 REST API로 인식
                .group("REST API")
                .build();
    }

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/**/*") // api가 포함되지 않은 경우 COMMON API로 인식
                .pathsToExclude("/api/**/*")
                .group("COMMON API")
                .build();
    }
}
