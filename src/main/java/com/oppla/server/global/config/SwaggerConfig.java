package com.oppla.server.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Value("${spring.datasource.username}")
    private String dbUsername;


    @Bean
    public OpenAPI api() {
        Info info = new Info()
                .version("v1.0.0 [Beta]")
                .title("OPPLA")
                .description("OPPLA API Swagger " +
                        "\nnow Profiles Active: " + dbUsername);

        // Authentication
        String jwtSchemeName = "AccessToken";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    public GroupedOpenApi allGroup(){
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi answerGroup(){
        return GroupedOpenApi.builder()
                .group("Answer")
                .pathsToMatch("/answer/**")
                .build();
    }

}