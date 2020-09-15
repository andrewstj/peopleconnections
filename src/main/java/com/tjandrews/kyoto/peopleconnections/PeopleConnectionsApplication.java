package com.tjandrews.kyoto.peopleconnections;

import com.fasterxml.classmate.TypeResolver;
import com.tjandrews.kyoto.peopleconnections.infrastructure.models.Person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PeopleConnectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleConnectionsApplication.class, args);
	}

	@Bean
	public Docket apiDocket(TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tjandrews.kyoto.peopleconnections")).build()
				.additionalModels(typeResolver.resolve(Person.class));
	}
}
