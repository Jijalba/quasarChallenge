package com.meli.quasarchallenge.infrastructure;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.meli.quasarchallenge.controllers"))
				.paths(PathSelectors.any())
				.build().apiInfo(getApiInfo());

	}
	
	 private ApiInfo getApiInfo() {
	        return new ApiInfo(
	                "Aplicación para el challenge Fuego de Quasar de Mercado Libre",
	                "Se reciben transmisiones a satélites desplegados y se trata de decodificar el mensaje que se posee, como así también triangular la ubicación de la fuente",
	                "Tecnologías: Java Spring Boot, Maven, Jpa, JUnit Test, MYSQL, PhpMyAdmin, Swagger",
	                "",
	                new Contact("Joaquín Ijalba", "https://www.linkedin.com/in/joaquin-alberto-ijalba/", "joaquinijalba@gmail.com"),
	                "",
	                "",
	                Collections.emptyList()
	        );
	    }

}
