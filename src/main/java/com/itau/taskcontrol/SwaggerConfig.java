package com.itau.taskcontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(true)
				.build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("").globalOperationParameters(aParameters)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Task Control API",
				"It is a demonstrative REST API application for task management developed using Spring Boot with H2 database and OAuth2 to authentication.",
				"0.0.1", "https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Caio Cesar Fantini", "https://github.com/ccfantini", "ccfantini@gmail.com"),
				"https://www.apache.org/licenses/LICENSE-2.0", "https://www.apache.org/licenses/LICENSE-2.0",
				Collections.emptyList());
	}

}