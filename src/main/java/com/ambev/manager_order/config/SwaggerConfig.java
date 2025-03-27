package com.ambev.manager_order.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Manager API")
                        .version("1.0")
                        .description("API para gerenciamento de pedidos")
                        .contact(new Contact()
                                .name("Reinaldo Viana")
                                .email("reinaldojun@gmail.com")
                                .url("https://www.linkedin.com/in/reinaldoviana/")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("order-manager-public")
                .pathsToMatch("/orders/**")
                .build();
    }
}
