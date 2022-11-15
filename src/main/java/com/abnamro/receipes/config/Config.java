package com.abnamro.receipes.config;

import org.modelmapper.ModelMapper;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class Config {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("online-recipes").pathsToMatch("/api/**").build();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Online Recipes API Information").version("v0.0.1")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("Recipes API Information Documentation"));
	}

}
